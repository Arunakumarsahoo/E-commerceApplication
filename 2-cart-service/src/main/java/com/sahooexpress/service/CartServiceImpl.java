package com.sahooexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sahooexpress.dto.CartItemRequestDTO;
import com.sahooexpress.dto.CartItemResponseDTO;
import com.sahooexpress.dto.UserDto;
import com.sahooexpress.feignclients.ProductFeignClient;
import com.sahooexpress.feignclients.UserFeignClient;
import com.sahooexpress.models.CartItem;
import com.sahooexpress.repository.CartItemRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.experimental.var;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@Autowired
	private UserFeignClient userFeignClient;

	private Long productId;

	@Override
	@CircuitBreaker(name = "productServiceCB", fallbackMethod = "addToCartFallBack")
	public CartItemResponseDTO addToCart(CartItemRequestDTO request) {
		
		//ToDo 1. ProductId is exists in db or not using RestTemplate
		
		checkProductExistsUsingFeign(request.getProductId());
		//checkProductExist(request.getProductId());
		var userDto = checkUserExistOrNot(request.getUserId());
		
		CartItem item;
		
		Optional<CartItem> existingItem = cartItemRepository.findByUserIdAndProductId(request.getUserId(), request.getProductId());
		
		if(existingItem.isPresent()) {
			item = existingItem.get();
			item.setQuantity(item.getQuantity()+request.getQuantity());
		}else {
			item = new CartItem();
			BeanUtils.copyProperties(request, item); 
		}
		//1. Way to save data into db
//		item.setUserId(request.getUserId());
//		item.setProductId(request.getProductId());
//		item.setQuantity(request.getQuantity());
		
		//CartItem savedCartItem = cartItemRepository.save(item);
		
		//2. way to save data into db
		
		CartItem saved = cartItemRepository.save(item);
		
		var dto = mapToResponseDTO(saved);
		dto.setUserDto(userDto);
		return dto;
	}
	
	private void checkProductExistsUsingFeign(Long productId) {
		boolean productExists = productFeignClient.isProductExists(productId);
		if(!productExists) {
			throw new RuntimeException("Product Not exist in db");
		}
		
	}

	private UserDto checkUserExistOrNot(Long userId) {
		ResponseEntity<UserDto> responseEntity = userFeignClient.findById(userId);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			UserDto result = responseEntity.getBody();
			return result;
		}
		return null;
		
	}

	private void checkProductExist(Long productId) {
		 String endpoint = "http://product-service/products/exists"+productId;
		 
		 ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(endpoint, Boolean.class);
		 if(responseEntity.getStatusCode().is2xxSuccessful()) {
			 Boolean isProductExists = responseEntity.getBody();
			 if(!isProductExists) {
				 throw new RuntimeException("Product Not exist in db");
			 }
		 }
		
	}

	private CartItemResponseDTO mapToResponseDTO(CartItem cartItem) {
		CartItemResponseDTO dto = new CartItemResponseDTO();
		BeanUtils.copyProperties(cartItem, dto);
		return dto;
	}

	@Override
	public CartItemResponseDTO updateQuantity(CartItemRequestDTO request) {
		CartItem existingItem = 
				cartItemRepository.findByUserIdAndProductId(request.getUserId(), request.getProductId())
				.orElseThrow(() -> new RuntimeException("Item not in Cart"));
		existingItem.setQuantity(request.getQuantity());
		return mapToResponseDTO(cartItemRepository.save(existingItem));
	}

	@Override
	@Transactional
	public void removeItem(Long userId, Long productId) {
		cartItemRepository.deleteByuserIdAndProductId(userId, productId);
		
	}

	@Override
	public List<CartItemResponseDTO> getUserCart(Long userId) {
		return cartItemRepository.findByUserId(userId)
				.stream().map(this::mapToResponseDTO)
				.toList();
	}

	@Override
	@Transactional
	public void clearCart(Long userId) {
		cartItemRepository.deleteByUserId(userId);
		
	}
	
	public CartItemResponseDTO addToCartFallBack(CartItemRequestDTO request, Throwable ex) {
		CartItemResponseDTO response = new CartItemResponseDTO();
		response.setMessage("Product Service is currently unavailable.Please try after sometime");
		return response;
	
	}
	

}

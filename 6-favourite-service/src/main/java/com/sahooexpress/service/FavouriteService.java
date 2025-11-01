package com.sahooexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahooexpress.dto.FavouriteDTO;
import com.sahooexpress.dto.UserDto;
import com.sahooexpress.feignclients.ProductFeignClient;
import com.sahooexpress.feignclients.UserFeignClient;
import com.sahooexpress.models.Favourite;
import com.sahooexpress.repository.FavouriteRepository;

import lombok.experimental.var;

@Service
public class FavouriteService {
	
	@Autowired
	private FavouriteRepository favouriteRepository;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public FavouriteDTO addProductToWishList(Integer userId,Long productId) {
		// check product exits or not
		// check user exits or not
		boolean productExits = productFeignClient.isProductExists(productId);
		UserDto userDto = userFeignClient.findById(userId);
		
		if(userDto == null) {
			throw new RuntimeException("User Not Found in db");
		}
		
		if(!productExits) {
			throw new RuntimeException("Product not exits");
		}
		Favourite favourite = favouriteRepository.findByUserIdAndProductId(userId, productId)
					.orElse(new Favourite());
		favourite.setUserId(userId);
		favourite.setProductId(productId);
		
		favouriteRepository.save(favourite);
		var response =  mapToDTo(favourite);
		response.setUserDto(userDto);
		return response;
		
	}

	private FavouriteDTO mapToDTo(Favourite favourite) {
		FavouriteDTO dto = new FavouriteDTO();
		dto.setProductId(favourite.getProductId());
		dto.setUserId(favourite.getUserId());
		return dto;
	}
	
	
	public void removeProductFromWishList(Integer userId,Long productId) {
		Favourite dbFavourite = favouriteRepository.findByUserIdAndProductId(userId, productId)
		.orElseThrow(() -> new RuntimeException("Product Not found in wishlist"));
		
		favouriteRepository.delete(dbFavourite);
	}

}

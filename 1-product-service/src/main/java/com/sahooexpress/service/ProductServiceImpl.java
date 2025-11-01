package com.sahooexpress.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahooexpress.dto.ProductRequestDTO;
import com.sahooexpress.dto.ProductResponseDTO;
import com.sahooexpress.dto.ProductStockUpdateDTO;
import com.sahooexpress.model.Product;
import com.sahooexpress.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ProductResponseDTO addProduct(ProductRequestDTO request) {
		
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setStock(request.getStock());
		
		Product dbProduct = productRepository.save(product);
		return mapToDTo (dbProduct);
	}

	private ProductResponseDTO mapToDTo(Product dbProduct) {
		ProductResponseDTO dto = new ProductResponseDTO();
		BeanUtils.copyProperties(dbProduct, dto);
		return dto;
	}
	// product -> i need productname using java 8 (string)
	// streams -> map,filter,

	@Override
	public ProductResponseDTO getProductById(Long productId) {
		return productRepository.findById(productId)
				//.map(product -> mapToDTo(product)) // Lambda Expression
				.map(this::mapToDTo)//Method Reference - Alternative for lambda expression
				.orElseThrow(()-> new RuntimeException("Product Not Found"));
	}

	@Override
	public boolean isProductExists(Long productId) {
		
		return productRepository.existsById(productId);
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {
		return productRepository.findAll().stream()
				.map(this::mapToDTo)
				.toList();
	}

	@Override
	public void updateStock(List<ProductStockUpdateDTO> updates) {
		
		for(ProductStockUpdateDTO dto : updates) {
			Product product = productRepository.findById(dto.getProductId()).orElseThrow(()-> new RuntimeException("Product Not Found"));
			
			if (product.getStock() < dto.getQuantity()) {
				throw new RuntimeException("Insufficeint stock for productid");
			}
			
			product.setStock(product.getStock() - dto.getQuantity());
			productRepository.save(product);
		}
		
	}

}
//updateStock 

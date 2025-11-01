package com.sahooexpress.product.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sahooexpress.product.dto.ProductRequestDTO;
import com.sahooexpress.product.dto.ProductResponseDTO;

@FeignClient(name="PRODUCT-SERVICE",path="/products")
public interface ProductFeignClient {
	
	@PostMapping
	public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO request);

}

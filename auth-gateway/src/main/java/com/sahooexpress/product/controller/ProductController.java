package com.sahooexpress.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.product.dto.ProductRequestDTO;
import com.sahooexpress.product.dto.ProductResponseDTO;
import com.sahooexpress.product.service.ProductFeignClient;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@PostMapping
	public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO request) {
		log.info("Auth Gateway ProductController addProduct");
		return new ResponseEntity<>(productFeignClient.addProduct(request),HttpStatus.CREATED);
	}
	

}

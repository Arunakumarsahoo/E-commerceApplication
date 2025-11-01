package com.sahooexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.dto.ProductRequestDTO;
import com.sahooexpress.dto.ProductResponseDTO;
import com.sahooexpress.dto.ProductStockUpdateDTO;
import com.sahooexpress.repository.ProductRepository;
import com.sahooexpress.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
    @PostMapping
	public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO request) {
		log.info("ProductController addProduct");
		productService.addProduct(request);
		return new ResponseEntity<>(productService.addProduct(request),HttpStatus.CREATED);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long productId) {
		log.info("ProductController getProduct");
		return ResponseEntity.ok(productService.getProductById(productId));
	}
	
	@GetMapping
	public List<ProductResponseDTO> getAllProducts() {
		log.info("ProductController getAllProducts");
	   return productService.getAllProducts();
	}
	
	@GetMapping("/exists/{productId}")
	public boolean isProductExists(@PathVariable Long productId) {
		log.info("ProductController isProductExists");
		return productService.isProductExists(productId);
		
	}
	
	@PutMapping("/update-stock")
	public ResponseEntity<Void> updateProductStock(@RequestBody List<ProductStockUpdateDTO> request) {
		log.info("ProductController updateProductStock");
		productService.updateStock(request);
		return ResponseEntity.ok().build();
	}

}

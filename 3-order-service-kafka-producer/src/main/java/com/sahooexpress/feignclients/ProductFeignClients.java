package com.sahooexpress.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sahooexpress.dto.ProductResponseDTO;
import com.sahooexpress.dto.ProductStockUpdateDTO;

@FeignClient(name = "product-service", path = "/products")
public interface ProductFeignClients {
	
	@GetMapping("/{productId}")
	public ProductResponseDTO getProduct(@PathVariable Long productId);
	
	@PutMapping("/update-stock")
	ResponseEntity<Void> updateProductStock(@RequestBody List<ProductStockUpdateDTO> request);

}

package com.sahooexpress.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product-service",path = "/products")
//@FeignClient(path = "/product",url = "http://localhost:8041")
public interface ProductFeignClient {
	
	@GetMapping("/exists/{productId}")
	public boolean isProductExists(@PathVariable Long productId);

}

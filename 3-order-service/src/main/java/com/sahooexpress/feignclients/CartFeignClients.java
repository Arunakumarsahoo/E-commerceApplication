package com.sahooexpress.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sahooexpress.dto.CartItemResponseDTO;


@FeignClient(name = "cart-service", path = "/cart")
public interface CartFeignClients {
	
	@GetMapping("{userId}")
	public List<CartItemResponseDTO> getCartByUserId(@PathVariable Long userId);
	
	@DeleteMapping("/clear/{userId}")
	public Void clearUserCart(@PathVariable Long userId);

}

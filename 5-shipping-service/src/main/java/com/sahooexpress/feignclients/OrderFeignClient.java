package com.sahooexpress.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.sahooexpress.dto.OrderResponseDTO;

@FeignClient(name="order-service",path="/orders")
public interface OrderFeignClient {
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long orderId);
	
	@PutMapping("/{orderId}/status/{status}")
	public void updateOrderStatus(@PathVariable Long orderId, @PathVariable String status);


}

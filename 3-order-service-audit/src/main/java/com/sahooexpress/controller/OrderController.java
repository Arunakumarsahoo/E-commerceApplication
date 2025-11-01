package com.sahooexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.dto.OrderResponseDTO;
import com.sahooexpress.dto.PlaceOrderRequestDTO;
import com.sahooexpress.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping
	public OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO request) {
		log.info("OrderController placeOrder");
		OrderResponseDTO response = orderService.placeOrder(request);
		return response;
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long orderId) {
		log.info("OrderController getOrder");
		return ResponseEntity.ok(orderService.getOrderById(orderId));
	}

	// mandatory to implement
	@PutMapping("/{orderId}/status/{status}")
	public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
		log.info("OrderController updateOrderStatus");
		orderService.updateOrderStatus(orderId, status);
		return ResponseEntity.noContent().build(); // 204 No Content
	}

}

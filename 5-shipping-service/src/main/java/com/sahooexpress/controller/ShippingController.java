package com.sahooexpress.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.dto.ShippingRequestDTO;
import com.sahooexpress.dto.ShippingResponseDTO;
import com.sahooexpress.service.ShippingService;



@RestController
@RequestMapping("/shipping")
public class ShippingController {
	
	// creating a logger
    Logger log
        = LoggerFactory.getLogger(ShippingController.class);

	
	@Autowired
	private ShippingService shippingService;

	@PostMapping("/ship/{orderId}")
	public ShippingResponseDTO shipOrder(@PathVariable Long orderId,@RequestBody ShippingRequestDTO request) {
		log.info("ShippingController shipOrder");
		return shippingService.shipOrder(orderId, request);
	}
	
}

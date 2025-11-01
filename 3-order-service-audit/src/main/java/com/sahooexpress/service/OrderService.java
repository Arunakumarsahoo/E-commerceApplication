package com.sahooexpress.service;

import com.sahooexpress.dto.OrderResponseDTO;
import com.sahooexpress.dto.PlaceOrderRequestDTO;

public interface OrderService {
	
	OrderResponseDTO placeOrder(PlaceOrderRequestDTO request);
	OrderResponseDTO getOrderById(Long orderId);
	public void updateOrderStatus(Long orderId, String status);

}

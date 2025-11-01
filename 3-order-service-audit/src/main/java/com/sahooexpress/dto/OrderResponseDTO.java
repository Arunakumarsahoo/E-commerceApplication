package com.sahooexpress.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderResponseDTO {
	
	private Long orderId;
	private Long userId;
	private BigDecimal totalPrice;
	private String status;
	private List<OrderItemResponseDTO> items;

}

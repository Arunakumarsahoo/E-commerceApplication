package com.sahooexpress.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentRequestDTO {
	
	private Long orderId;
	
	private Long userId;
	
	private BigDecimal amount;
	
	private String paymentMethod; //CARD,UPI..
	

}

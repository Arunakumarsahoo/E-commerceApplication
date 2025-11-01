package com.sahooexpress.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PaymentResponseDTO {
	
	private Long paymentId;
	private Long orderId;
	private Long userId;
	private BigDecimal amount;
	private String status; // SUCCESS,FAILED,PENDING
	private LocalDateTime payDateTime;

}

package com.sahooexpress.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="payments")
@Setter
@Getter
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long orderId;
	
	private Long userId;
	
	private BigDecimal amount;
	
	private String status; // SUCCESS,FAILED,PENDING
	
	private String paymentMethod; //CARD,UPI..
	
	private LocalDateTime payDateTime = LocalDateTime.now();
	
}
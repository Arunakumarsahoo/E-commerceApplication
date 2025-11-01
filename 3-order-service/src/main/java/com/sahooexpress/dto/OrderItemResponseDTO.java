package com.sahooexpress.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemResponseDTO {
	
	private Long productId;
	private Integer quanity;
	private BigDecimal price;

}

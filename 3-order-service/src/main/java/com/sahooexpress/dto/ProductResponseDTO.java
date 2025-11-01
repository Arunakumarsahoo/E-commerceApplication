package com.sahooexpress.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDTO {
	
	private Long id;
	private String name;
	private String Description;
	private BigDecimal price;
	private Integer stock;
}

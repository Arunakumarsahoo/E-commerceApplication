package com.sahooexpress.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDTO {
	
	private Long id;
	private String name;
	private String Description;
	private Double price;
	private Integer stock;
}

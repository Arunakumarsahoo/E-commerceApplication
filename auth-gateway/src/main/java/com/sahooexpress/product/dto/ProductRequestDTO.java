package com.sahooexpress.product.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDTO {
	
	private String name;
	private String Description;
	private Double price;
	private Integer stock;

}

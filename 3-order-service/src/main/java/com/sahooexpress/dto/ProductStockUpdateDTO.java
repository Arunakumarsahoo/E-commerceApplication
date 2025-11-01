package com.sahooexpress.dto;

import lombok.AllArgsConstructor;

import lombok.Data;


@Data
@AllArgsConstructor
public class ProductStockUpdateDTO {

	private Long productId;
	private Integer quantity; //reduce
}

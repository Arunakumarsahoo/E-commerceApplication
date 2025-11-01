package com.sahooexpress.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductStockUpdateDTO {

	private Long productId;
	private Integer quantity; //reduce
}

package com.sahooexpress.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDTO {
	
	private Long userId;
	private Long productId;
	private Integer quantity;
	
	

}

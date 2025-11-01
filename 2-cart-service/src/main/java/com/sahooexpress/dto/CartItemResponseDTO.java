package com.sahooexpress.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDTO {
	
	private Integer id;
	private Long userId;
	private Long productId;
	private Integer quantity;
	private UserDto userDto;
	private String message;

}

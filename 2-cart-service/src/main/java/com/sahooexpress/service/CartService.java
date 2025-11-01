package com.sahooexpress.service;

import java.util.List;

import com.sahooexpress.dto.CartItemRequestDTO;
import com.sahooexpress.dto.CartItemResponseDTO;

public interface CartService {
	
	CartItemResponseDTO addToCart(CartItemRequestDTO request);
	CartItemResponseDTO updateQuantity(CartItemRequestDTO request);
	void removeItem(Long userId, Long productId);
	//assignment
	List<CartItemResponseDTO> getUserCart(Long userId); 

	void clearCart(Long userId);

}

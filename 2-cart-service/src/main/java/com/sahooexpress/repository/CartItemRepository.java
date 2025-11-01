package com.sahooexpress.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
	
	void deleteByuserIdAndProductId(Long userId,Long productId);
	
	void deleteByUserId(Long userId);
	
	List<CartItem> findByUserId(Long userId);

}

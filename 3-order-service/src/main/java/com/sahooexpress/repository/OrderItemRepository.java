package com.sahooexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}

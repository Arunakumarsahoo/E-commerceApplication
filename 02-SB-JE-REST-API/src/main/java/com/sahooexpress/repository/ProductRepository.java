package com.sahooexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	

}

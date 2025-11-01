package com.sahooexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}

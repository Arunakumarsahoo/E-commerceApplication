package com.sahooexpress.repository;

//import java.util.Locale.Category;

import com.sahooexpress.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

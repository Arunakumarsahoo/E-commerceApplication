package com.sahooexpress.service;

//import com.sahooexpress.controllers.CategoryController;
import com.sahooexpress.entities.Category;
import com.sahooexpress.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahooexpress.repository.CategoryRepository;

@Service
public class CategoryService {

	
	@Autowired
	private CategoryRepository categoryRepository;

	
	public Category create(Category category) {
		return categoryRepository.save(category);
	}
	
	public Category fetch(Integer id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found exception"));
	}

}

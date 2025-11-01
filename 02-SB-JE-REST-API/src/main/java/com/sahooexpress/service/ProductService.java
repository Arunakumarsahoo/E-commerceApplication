package com.sahooexpress.service;

//import java.util.Locale.Category;
import com.sahooexpress.entities.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahooexpress.entities.Product;
import com.sahooexpress.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public void create(Product product) {
		 Integer categoryId = product.getCategory().getId();
		 Category dbCategory = categoryService.fetch(categoryId);
		 product.setCategory(dbCategory);
		 productRepository.save(product);
	}
}

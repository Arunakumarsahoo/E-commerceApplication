package com.sahooexpress.service;

import java.util.List;

import com.sahooexpress.dto.ProductRequestDTO;
import com.sahooexpress.dto.ProductResponseDTO;
import com.sahooexpress.dto.ProductStockUpdateDTO;

public interface ProductService {
	
	  ProductResponseDTO addProduct(ProductRequestDTO request);
	  ProductResponseDTO getProductById(Long productId);
	  boolean isProductExists(Long productId);
	  List<ProductResponseDTO> getAllProducts();
	  void updateStock(List<ProductStockUpdateDTO> updates);

}

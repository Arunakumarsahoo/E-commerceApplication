package com.sahooexpress.service;

import com.sahooexpress.dto.ShippingRequestDTO;
import com.sahooexpress.dto.ShippingResponseDTO;

public interface ShippingService {
	
	ShippingResponseDTO shipOrder(Long orderId,ShippingRequestDTO request);
	
	// assingment
		ShippingResponseDTO getShippingDetails(Long orderId);
		
		void updateShippingStatus(Long orderId,String status);

}

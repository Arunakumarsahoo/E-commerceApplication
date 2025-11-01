package com.sahooexpress.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sahooexpress.dto.OrderResponseDTO;
import com.sahooexpress.dto.ShippingRequestDTO;
import com.sahooexpress.dto.ShippingResponseDTO;
import com.sahooexpress.feignclients.OrderFeignClient;
import com.sahooexpress.models.ShippingInfo;
import com.sahooexpress.repository.ShippingRepository;

@Service
public class ShippingServiceImpl implements ShippingService {
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	@Autowired
	private OrderFeignClient orderFeignClient;

	@Override
	public ShippingResponseDTO shipOrder(Long orderId, ShippingRequestDTO request) {
		ResponseEntity<OrderResponseDTO> responseEntity = orderFeignClient.getOrder(orderId);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			OrderResponseDTO orderResponseDTO = responseEntity.getBody();
			if(orderResponseDTO == null) {
				throw new RuntimeException("Order Not Found");
			}
	}
		ShippingInfo shippingInfo = new ShippingInfo();
		shippingInfo.setOrderId(orderId);
		shippingInfo.setShippedAt(LocalDateTime.now());
		shippingInfo.setCarrier(request.getCarrier());
		shippingInfo.setStatus("SHIPPED");
		shippingInfo.setTrackingNumber(generateTrackingNumber(orderId));
		shippingInfo.setShippingMethod(request.getShippingMethod());
		
		ShippingInfo dbShipping = shippingRepository.save(shippingInfo);
		
		orderFeignClient.updateOrderStatus(orderId, "SHIPPED");
		
		return mapToDTO(dbShipping);
	
	

}

	private ShippingResponseDTO mapToDTO(ShippingInfo dbShipping) {
		ShippingResponseDTO response = new ShippingResponseDTO();
		BeanUtils.copyProperties(dbShipping, response);
		return response;
	}
	
	private String generateTrackingNumber(Long orderId) {
		return "TRACK-"+orderId+"-"+System.currentTimeMillis();
	}

	@Override
	public ShippingResponseDTO getShippingDetails(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateShippingStatus(Long orderId, String status) {
		// TODO Auto-generated method stub
		
	}
	
}

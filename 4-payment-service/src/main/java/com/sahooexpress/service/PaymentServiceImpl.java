package com.sahooexpress.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahooexpress.dto.PaymentRequestDTO;
import com.sahooexpress.dto.PaymentResponseDTO;
import com.sahooexpress.models.Payment;
import com.sahooexpress.repository.PaymentRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
		
		checkOrderExistsOrNot(request.getOrderId());
		checkUserExistsOrNot(request.getUserId());
		
		Payment payment = new Payment();
		BeanUtils.copyProperties(request, payment);
		payment.setStatus("SUCCESS");
		
		Payment dbPayment = paymentRepository.save(payment);
	
		return mapToDTO(dbPayment);
		
	}

	private void checkUserExistsOrNot(Long userId) {
		// User Verification 
				// Feign Client
		
	}

	private void checkOrderExistsOrNot(Long orderId) {
		// Assingment - Feign Client
		
	}

	private PaymentResponseDTO mapToDTO(Payment dbPayment) {
		PaymentResponseDTO dto = new PaymentResponseDTO();
		BeanUtils.copyProperties(dbPayment, dto);
		dto.setPaymentId(dbPayment.getId());
		return dto;
	
	}

	@Override
	public PaymentResponseDTO getPaymentDetails(Long orderId) {
		return null;
	}
	
	

}

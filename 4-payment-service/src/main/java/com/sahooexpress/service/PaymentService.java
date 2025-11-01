package com.sahooexpress.service;

import com.sahooexpress.dto.PaymentRequestDTO;
import com.sahooexpress.dto.PaymentResponseDTO;


public interface PaymentService {
	
	PaymentResponseDTO processPayment(PaymentRequestDTO request);
	
	//Assignment
	PaymentResponseDTO getPaymentDetails(Long orderId);

}

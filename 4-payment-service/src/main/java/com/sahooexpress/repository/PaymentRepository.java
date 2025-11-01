package com.sahooexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}

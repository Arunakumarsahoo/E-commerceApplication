package com.sahooexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.models.ShippingInfo;

public interface ShippingRepository extends JpaRepository<ShippingInfo, Long>{

}

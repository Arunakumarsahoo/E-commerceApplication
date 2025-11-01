package com.sahooexpress.dto;

public class ShippingRequestDTO {
	
	private Long orderId;
	private String shippingMethod; // STANDARD,NEXT-DAY
	private String carrier; // FedEx,Shadow..
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

}

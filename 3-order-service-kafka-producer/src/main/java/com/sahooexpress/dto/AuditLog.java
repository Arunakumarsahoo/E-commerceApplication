package com.sahooexpress.dto;

import java.util.Date;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuditLog {
	
	private String id;               // Unique ID (MongoDB _id)
    private String serviceName;      // e.g., "order-service", "product-service"
    private String action;           // e.g., "product created", "order created", "payment success"
    private String userId;           // e.g., "501", "678", "8999"
    private Date timestamp;          // Event timestamp (usually current time)
    private Map<String, Object> details;  // Optional: Details about the action (e.g., changed fields)
    private String ipAddress;        // Optional: IP address of the requester
	
	public AuditLog(String id, String serviceName, String action, String userId, Date timestamp) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.action = action;
		this.userId = userId;
		this.timestamp = new Date();
	}

}

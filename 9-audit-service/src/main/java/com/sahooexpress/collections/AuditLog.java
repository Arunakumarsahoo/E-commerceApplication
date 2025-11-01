package com.sahooexpress.collections;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "auditevents")
public class AuditLog {
	
	@Id
	private String id;
	private String serviceName; // order-service,payment-service..
	private String action; // product-created,order-placed,shipped
	private String userId; // logged userid
	private Date timestamp;
	
	public AuditLog(String id, String serviceName, String action, String userId) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.action = action;
		this.userId = userId;
		this.timestamp = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	

}

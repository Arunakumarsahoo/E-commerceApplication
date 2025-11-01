package com.sahooexpress.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.sahooexpress.collections.AuditLog;
import com.sahooexpress.service.AuditService;

@Component
public class AuditKafkaConsumer {
	
	@Autowired
	private AuditService auditService;

	@KafkaListener(topics = "audit-event",groupId = "audit-group")
	public void consume(@Payload AuditLog auditLog) {
		System.out.println("Received Audit Event "+auditLog.getAction());
		auditService.logEvent(auditLog);
	}
	
	@KafkaListener(topics = "audit-event",groupId = "notification-group")
	public void consumeNotificationPurpose(@Payload AuditLog auditLog) {
		System.out.println("Notificatoin Group received event "+auditLog.getAction());
	}

}

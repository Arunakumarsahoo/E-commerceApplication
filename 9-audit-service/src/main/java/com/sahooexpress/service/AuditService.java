package com.sahooexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahooexpress.collections.AuditLog;
import com.sahooexpress.repository.AuditLogRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuditService {
	
	@Autowired
	private AuditLogRepository auditLogRepository;
	
	public AuditLog logEvent(AuditLog log) {
		//log.setTimestamp(new Date());
		return auditLogRepository.save(log);
	}
	

}

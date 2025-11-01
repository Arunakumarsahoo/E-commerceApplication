package com.sahooexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.collections.AuditLog;
import com.sahooexpress.service.AuditService;

@RestController
@RequestMapping("/api/audits")
public class AuditController {
	
	@Autowired
	private AuditService auditService;
	
	@PostMapping("/log")
	public AuditLog publishAuditEvent(@RequestBody AuditLog log) {
		return auditService.logEvent(log);
	}

}

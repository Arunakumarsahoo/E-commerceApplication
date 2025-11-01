package com.sahooexpress.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sahooexpress.dto.AuditLog;

@FeignClient(name = "audit-service") // Replace with Eureka name or actual host
public interface AuditFeignClient {
	
	@PostMapping("/api/audits/log")
    public AuditLog publishAuditEvent(@RequestBody AuditLog log);

}

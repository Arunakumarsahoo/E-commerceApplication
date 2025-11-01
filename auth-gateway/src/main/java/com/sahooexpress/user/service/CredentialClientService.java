package com.sahooexpress.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sahooexpress.user.dto.CredentialDto;

@FeignClient(name="USER-SERVICE",path="/api/credentials")
public interface CredentialClientService {

	@GetMapping("username/{uname}")
	public ResponseEntity<CredentialDto> findByUsername(@PathVariable("uname") String username);
}

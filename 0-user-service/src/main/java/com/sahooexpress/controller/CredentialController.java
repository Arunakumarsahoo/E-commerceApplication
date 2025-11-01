package com.sahooexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.dto.CredentialDto;
import com.sahooexpress.service.CredentialService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/credentials")
@Slf4j
public class CredentialController {
	
	@Autowired
	private CredentialService credentialServiceImpl;
	
	// Mandatory to implement
	@GetMapping("username/{uname}")
	public ResponseEntity<CredentialDto> findByUsername(@PathVariable("uname") String username) {
		log.info("CredentialController findByUsername");
		CredentialDto result = credentialServiceImpl.findByUsername(username);
		return ResponseEntity.ok(result);
	}

}

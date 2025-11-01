package com.sahooexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahooexpress.dto.CredentialDto;
import com.sahooexpress.mapper.CredentialMapper;
import com.sahooexpress.repository.CredentialRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CredentialService {
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	@Autowired
	private CredentialMapper credentialMapper;
	
	public CredentialDto findByUsername(String username) {
		
		log.info("CredentialService :: findByUsername {} ", username);
		return credentialRepository.findByUsername(username)
				.map(credentialMapper::toDto)
				.orElseThrow(()-> new RuntimeException("Username is not found in db"));
		
	}

}

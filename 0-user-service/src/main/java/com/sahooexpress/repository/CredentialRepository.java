package com.sahooexpress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.models.Credential;
import com.sahooexpress.models.User;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {
	
	Optional<Credential> findByUsername(String username);
	
	

}

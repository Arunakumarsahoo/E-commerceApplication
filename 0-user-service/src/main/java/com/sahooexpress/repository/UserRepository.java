package com.sahooexpress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	//DSL
	Optional<User> findByCredentialUsername(String username);
	
	User findByEmail(String email);

}

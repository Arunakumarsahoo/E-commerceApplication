package com.sahooexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.dto.UserDto;
import com.sahooexpress.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
		log.info("UserController save");
		UserDto dbUser = userService.save(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dbUser);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> findById(@PathVariable Integer userId) {
		log.info("UserController findById");
		return ResponseEntity.ok(userService.findById(userId));
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDto> deleteById(@PathVariable Integer userId) {
		log.info("UserController deleteById");
		return ResponseEntity.ok(userService.findById(userId));
	}

}

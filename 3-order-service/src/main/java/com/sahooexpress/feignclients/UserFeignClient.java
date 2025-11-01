package com.sahooexpress.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sahooexpress.dto.UserDTO;

@FeignClient(name = "user-service", path = "/api/users")
public interface UserFeignClient {
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer userId);
}
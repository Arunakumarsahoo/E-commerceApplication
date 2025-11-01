package com.sahooexpress.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sahooexpress.dto.UserDto;

@FeignClient(name = "user-service", path = "/api/users")
public interface UserFeignClient {
	
	@GetMapping("/{userId}")
	public UserDto findById(@PathVariable Integer userId);
}
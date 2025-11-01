package com.sahooexpress.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sahooexpress")
public class HelloWorldController {
	
	@GetMapping("/docker")
	public String getHello() {
		return "Welcome to Docker Course";
	}
	
	@GetMapping("/docker1")
	public String getHello1() {
		return "Welcome to Docker Course1";
	}

}

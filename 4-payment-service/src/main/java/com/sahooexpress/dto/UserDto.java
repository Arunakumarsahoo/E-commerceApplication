package com.sahooexpress.dto;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private Integer userId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String contact;
	
	
}

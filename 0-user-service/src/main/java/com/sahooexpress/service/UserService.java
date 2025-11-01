package com.sahooexpress.service;

import com.sahooexpress.dto.UserDto;

public interface UserService {
	
	UserDto save(UserDto userDto);
	UserDto findById(Integer userId);
	UserDto findByUsername(String username);
	void deleteById(Integer userId);
	UserDto update(Integer userId, UserDto userDto);

}

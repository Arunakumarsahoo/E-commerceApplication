package com.sahooexpress.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import com.sahooexpress.dto.UserDto;
import com.sahooexpress.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	
	@Mappings({
		@Mapping(source = "email", target = "emailAddress"),
		@Mapping(source = "phone", target = "contact")
	})
	UserDto toDto(User user);
	
	@Mappings({
		@Mapping(source = "emailAddress", target = "email"),
		@Mapping(source = "contact", target = "phone")
	})
	User toEntity(UserDto userDto);

}

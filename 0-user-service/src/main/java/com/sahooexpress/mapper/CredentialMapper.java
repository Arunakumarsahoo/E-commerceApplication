package com.sahooexpress.mapper;

import org.mapstruct.Mapper;

import com.sahooexpress.dto.CredentialDto;
import com.sahooexpress.models.Credential;


@Mapper(componentModel = "spring")
public interface CredentialMapper {

	CredentialDto toDto(Credential credential);
}
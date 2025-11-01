package com.sahooexpress.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CredentialDto {
	
    private Integer credentialId;
	private String username;
	private String password;
	private RoleBasedAuthority roleBasedAuthority;

}

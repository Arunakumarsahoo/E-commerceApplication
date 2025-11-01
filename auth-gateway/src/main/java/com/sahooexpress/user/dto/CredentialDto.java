package com.sahooexpress.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialDto {
	
    private Integer credentialId;
	private String username;
	private String password;
	private RoleBasedAuthority roleBasedAuthority;

}

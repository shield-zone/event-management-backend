package com.shield.eventmanagement.security.Dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String password;
}

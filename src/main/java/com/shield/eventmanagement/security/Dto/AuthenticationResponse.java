package com.shield.eventmanagement.security.Dto;

import org.springframework.http.HttpStatus;

import com.shield.eventmanagement.entities.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

	private User user;
	
	private String token;
	
	private HttpStatus status;
}

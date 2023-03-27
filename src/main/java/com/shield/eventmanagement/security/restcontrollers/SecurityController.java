package com.shield.eventmanagement.security.restcontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.exceptions.InvalidException;
import com.shield.eventmanagement.repositories.user.UserRepository;
import com.shield.eventmanagement.security.Dto.AuthenticationRequest;
import com.shield.eventmanagement.security.Dto.AuthenticationResponse;
import com.shield.eventmanagement.security.util.JwtUtil;

@RestController
@RequestMapping("/api/v1/secure")
public class SecurityController {

	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@Valid @RequestBody AuthenticationRequest request) throws InvalidException
	{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
					);
		}
		catch(Exception ex)
		{
			throw new InvalidException("Invalid username and password");
		}
		
		String token = jwtUtil.generateToken(request.getUserName());
		
		User user = userRepository.findByUserName(request.getUserName());
		
		AuthenticationResponse response = AuthenticationResponse.builder()
										 .user(user)
										 .token(token)
										 .status(HttpStatus.OK)
										 .build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

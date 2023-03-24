package com.shield.eventmanagement.restcontrollers.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.repositories.user.UserRepository;
import com.shield.eventmanagement.request.user.UserDto;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto)
	{
		
		User user = User
				    .builder()
				    .userName(userDto.getUserName())
				    .isActive(true)
				    .role(userDto.getRole())
				    .password(userDto.getPassword())
				    .build();
		user = userRepository.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetch-all")
	public ResponseEntity<?> getAll()
	{
		List<User> userList = userRepository.findAll();
		
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
}

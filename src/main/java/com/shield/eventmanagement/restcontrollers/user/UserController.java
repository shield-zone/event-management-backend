package com.shield.eventmanagement.restcontrollers.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.exceptions.user.UserNotFoundException;
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
				    .name(userDto.getName())
				    .role(userDto.getRole())
				    .password(userDto.getPassword())
				    .build();
		user = userRepository.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User userUpdateRequest){
		
		User user = userRepository.findById(userUpdateRequest.getUserId()).get();
		
		if(userUpdateRequest.getName()!=null)
		{
			user.setName(userUpdateRequest.getName());
		}
		
		if(userUpdateRequest.getPassword()!=null)
		{
			user.setPassword(userUpdateRequest.getPassword());
		}
		
		if(userUpdateRequest.getUserName()!=null)
		{
			user.setUserName(userUpdateRequest.getUserName());
		}
		
		user = userRepository.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/find-by-id")
	public ResponseEntity<?> findById(@RequestParam("userId") Long userId) throws UserNotFoundException
	{
		User user = userRepository.findById(userId).get();
		
		if(user==null)
		{
			throw new UserNotFoundException("User with the given Id not found");
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@GetMapping("/fetch-all")
	public ResponseEntity<?> getAll()
	{
		List<User> userList = userRepository.findAll();
		
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@RequestParam("userId") Long userId)
	{
		User user = userRepository.findById(userId).get();
		user.setActive(false);
		userRepository.save(user);
		String message = "User Deleted Successfully";
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}

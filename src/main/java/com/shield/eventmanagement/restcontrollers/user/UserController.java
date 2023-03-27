package com.shield.eventmanagement.restcontrollers.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.shield.eventmanagement.services.user.UserService;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto)
	{

		User user = userService.create(userDto);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/find-by-username")
	public ResponseEntity<?> fetchUserByUserName(@RequestParam("username") String username)
	{
		User user = userRepository.findByUserName(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User with give username not found");
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User userUpdateRequest) throws UserNotFoundException{
		
		
		User user = userService.update(userUpdateRequest);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/find-by-id")
	public ResponseEntity<?> findById(@RequestParam("userId") Long userId) throws UserNotFoundException
	{
		User user = userService.fetchById(userId);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@GetMapping("/fetch-all")
	public ResponseEntity<?> getAll()
	{
		List<User> userList = userService.fetchAll();
		
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@RequestParam("userId") Long userId) throws UserNotFoundException
	{
		User user = userService.fetchById(userId);
		user.setActive(false);
		user = userService.update(user);
		String message = "User Deleted Successfully";
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}

package com.shield.eventmanagement.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.repositories.user.UserRepository;
import com.shield.eventmanagement.security.entities.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserName(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User does exist with the given username");
		}
		
		UserDetails userDetails = new CustomUserDetails(user);
		
		return userDetails;
	}

}

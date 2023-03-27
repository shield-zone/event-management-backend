package com.shield.eventmanagement.services.user;

import java.util.List;

import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.exceptions.user.UserNotFoundException;
import com.shield.eventmanagement.request.user.UserDto;

public interface UserService {

    User create(UserDto userDto);
    
    User update(User userUpdateRequest) throws UserNotFoundException;
	
	User fetchById(Long userId) throws UserNotFoundException;
	
	List<User> fetchAll();
	
}

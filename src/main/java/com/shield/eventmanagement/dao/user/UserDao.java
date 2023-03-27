package com.shield.eventmanagement.dao.user;

import java.util.List;

import com.shield.eventmanagement.entities.user.User;

public interface UserDao {

	User save(User user);
	
	User findById(Long userId);
	
	List<User> findAll();

}

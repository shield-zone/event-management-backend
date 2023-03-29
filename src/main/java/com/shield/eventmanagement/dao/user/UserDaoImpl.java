package com.shield.eventmanagement.dao.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.repositories.user.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User save(User user) {
		
		user = userRepository.save(user);	
		return user;
	}


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> userList = userRepository.findAll();
		return userList;
	}


	@Override
	public Optional<User> findById(Long userId) {

		return userRepository.findById(userId);
	}

}

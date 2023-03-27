package com.shield.eventmanagement.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.dao.user.UserDao;
import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.exceptions.user.UserNotFoundException;
import com.shield.eventmanagement.request.user.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User create(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = User.builder().userName(userDto.getUserName()).isActive(true).name(userDto.getName())
				.role(userDto.getRole()).password(userDto.getPassword()).build();
		user = userDao.save(user);
		return user;
	}

	@Override
	public User update(User userUpdateRequest) throws UserNotFoundException {
		User user = userDao.findById(userUpdateRequest.getUserId());

		if (user == null) {
			throw new UserNotFoundException("User with given Id not found");
		}

		if (userUpdateRequest.getName() != null) {
			user.setName(userUpdateRequest.getName());
		}

		if (userUpdateRequest.getPassword() != null) {
			user.setPassword(userUpdateRequest.getPassword());
		}

		if (userUpdateRequest.getUserName() != null) {
			user.setUserName(userUpdateRequest.getUserName());
		}

		user = userDao.save(user);
		return user;
	}

	@Override
	public User fetchById(Long userId) throws UserNotFoundException {
		User user = userDao.findById(userId);

		if (user == null) {
			throw new UserNotFoundException("User with the given Id not found");
		}
		return user;
	}

	@Override
	public List<User> fetchAll() {
		
		List<User> userList = userDao.findAll();
		
		return userList;
	}

}

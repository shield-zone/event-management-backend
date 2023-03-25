package com.shield.eventmanagement.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shield.eventmanagement.entities.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserName(String userName);
}

package com.cognizant.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.authorization.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByUserName(String userName);
	
}
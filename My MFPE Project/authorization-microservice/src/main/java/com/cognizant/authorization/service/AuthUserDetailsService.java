package com.cognizant.authorization.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authorization.model.User;
import com.cognizant.authorization.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		log.info("UserDetails  class loadUserByUserName is invoked");
		User user=repo.findByUserName(username);
		log.info(user.toString());
		log.info("UserDetails  class loadUserByUserName is ended");
		return new AuthUserDetails(user);
	}

	@PostConstruct
	public void addUser() {
		log.info("UserDetails  class addUser is invoked");
		if(repo.findAll().isEmpty()) {
		User user1=new User(1, "Abhishek Tiwari", "abhishek", false, true, false, true, "USER");
		User user2=new User(2, "Aditya Namdev", "aditya", false, true, false, true, "USER");
		User user3=new User(3, "Adarsh Shridhar", "adarsh", false, true, false, true, "USER");
		User user4=new User(4, "Tanmay Sharma", "tanmay", false, true, false, true, "USER");
		repo.save(user1);
		repo.save(user2);
		repo.save(user3);
		repo.save(user4);
		log.info("UserDetails  class addUser is ended");
		}
	}
	
}
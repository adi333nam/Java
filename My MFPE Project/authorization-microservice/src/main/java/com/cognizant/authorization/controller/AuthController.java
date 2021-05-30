package com.cognizant.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authorization.exception.UserNotFoundException;
import com.cognizant.authorization.model.AuthToken;
import com.cognizant.authorization.model.Credentials;
import com.cognizant.authorization.service.AuthUserDetailsService;
import com.cognizant.authorization.util.JwtTokenUtil;

import org.springframework.security.core.userdetails.UsernameNotFoundException;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public")
public class AuthController {
	
	@Autowired
	private JwtTokenUtil tokenUtil;
	
	@Autowired
	private AuthUserDetailsService userDetailsService; 
	
	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/authenticate")
	public AuthToken authenticate(@RequestBody Credentials creds) throws UserNotFoundException
	{
		log.info("Auth Controller method authenticate get invoked");
		log.info(creds.toString());

		try
		{
			authManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),creds.getPassword()));
		}
		catch(UsernameNotFoundException e)
		{
			throw new UserNotFoundException("User Not Found");
		}
		catch(Exception e)
		{
			throw new UserNotFoundException("Bad Credentials");
		}
	
		
		
		UserDetails userDetails=userDetailsService.loadUserByUsername(creds.getUsername());
		
		String jwt=tokenUtil.generateToken(userDetails);
		log.info("Auth Controller method authenticate ended");
		return new AuthToken(jwt);
		
	}
	
	
	
}
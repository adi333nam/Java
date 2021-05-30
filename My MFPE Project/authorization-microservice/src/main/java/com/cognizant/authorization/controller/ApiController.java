package com.cognizant.authorization.controller;


import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authorization.model.JwtResponse;
import com.cognizant.authorization.util.JwtTokenUtil;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/authorize-header")
public class ApiController {
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@PostMapping("/validate")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") final String token) {
		log.debug("TOKEN : ", token);
		log.debug("TOKEN AFTER REMOVING BEARER : ", token);
		JwtResponse jwtResponse = new JwtResponse();
		boolean isValid=jwtUtil.validateToken(token);
		if (isValid) {
			log.info("TOKEN IS VALID");
			jwtResponse.setUserName(jwtUtil.extractUsername(token));
			jwtResponse.setValid(true);
			
			log.debug("JWTRESPONSE {}:", jwtResponse);
		} else {
			log.error("TOKEN VALIDATION FAILED");
			jwtResponse.setValid(false);
		}
		log.info("END");
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
	}
}
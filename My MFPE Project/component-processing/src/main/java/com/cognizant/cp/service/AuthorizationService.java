package com.cognizant.cp.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.cp.model.JwtResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthorizationService {

	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public boolean validateHeaderToken(String token)
	{
		String url="http://AUTHORIZATION-MICROSERVICE/authorize-header/validate";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", token);      

		HttpEntity<String> request = new HttpEntity<>(headers);
		
		try
		{
		JwtResponse jwtResponse=restTemplate.postForObject(url, request,JwtResponse.class);
		log.info("*****************JWT TOKEN IS VALID");
		if(jwtResponse==null)
		{
			throw new NullPointerException();
		}
		return jwtResponse.isValid();
		}
		catch(Exception e)
		{
			log.info("*****************JWT TOKEN IS NOT VALID");
		return  false;
		}
	}
	
}


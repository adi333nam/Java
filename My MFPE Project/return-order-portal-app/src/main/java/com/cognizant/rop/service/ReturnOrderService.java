package com.cognizant.rop.service;


import java.util.Collections;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.rop.model.*;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ReturnOrderService {

	@Autowired

	private RestTemplate restTemplate;
	private final static String HTTP="http://";
	private final static double CREDITLIMIT=100000;
	private final static String HOST="localhost";
    private final static String AUTH="Authorization";
	
	public boolean authenticateUser(String userName,String password,HttpSession session)
	{
		Credentials credentials=new Credentials(userName,password);
		String url=HTTP+HOST+":8888/public/authenticate";
		try
		{
		AuthToken authToken=restTemplate.postForObject(url, credentials,AuthToken.class);
		session.setAttribute("jwtToken", authToken.getJwtToken());
		log.info("---------------"+authToken);
		
		return true;
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			return false;
		}
		
	}

	
	public ProcessResponse getResponse(ProcessRequest processRequest,String token)
	{
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set(AUTH, token);      
		HttpEntity<ProcessRequest> request = new HttpEntity<>(processRequest, headers);
		
		String url=HTTP+HOST+":6010/component-processing/processDetail";
        return restTemplate.postForObject(url, request,ProcessResponse.class);	
     }

	public ProcessRequest getRequest(int userId,String token)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set(AUTH, token);      
		HttpEntity<ProcessRequest> request = new HttpEntity<>(headers);
		
		String url=HTTP+HOST+":6010/component-processing/getRequest/"+userId;
		return  restTemplate.postForObject(url,request,ProcessRequest.class);
	}
	
	public double getTotalPayment(int requestId,long creditCardNumber,double totalCharge,String token)
	{
        log.info("Class:ReturnOrderService   Method:getToalPayment invoked");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set(AUTH, token);      
		HttpEntity<ProcessRequest> request = new HttpEntity<>(headers);
		
		String url=HTTP+HOST+":6010/component-processing/completeProcessing/"+ requestId
				+ "/"+creditCardNumber+"/"+CREDITLIMIT+"/"+totalCharge;
	    log.info("Class:ReturnOrderService   Method:getToalPayment ended");
	    return restTemplate.postForObject(url,request,Double.class);
	     
	 
	}
	
   public double getCreditLimit()
   {
	   return CREDITLIMIT;
   }

}

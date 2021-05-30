package com.cognizant.cp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PaymentService {

	@Autowired
	private RestTemplate restTemplate;
	
	public double getPayment(long creditCardNumber,double creditLimit,double processingCharge)
	{
		String url="http://PAYMENT-SERVICE/payment/getPayment/"+creditCardNumber+ "/" +creditLimit+"/"+processingCharge;
	    return restTemplate.getForObject(url,Double.class);
	}
	
	
	
}

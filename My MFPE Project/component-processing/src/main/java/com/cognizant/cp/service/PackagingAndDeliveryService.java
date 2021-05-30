package com.cognizant.cp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.cp.model.ComponentType;

@Service
public class PackagingAndDeliveryService {

	@Autowired
	private RestTemplate restTemplate;
	
	public double getPackagingAndDeliveryCharges(ComponentType componentType,int quantity)
	{
		String url="http://PACKAGINGANDDELIVERY-SERVICE/packaging-and-delivery/packagingAndDeliveryService/"
				+ componentType+ "/" +quantity;
		
	  return restTemplate.getForObject(url,Double.class);
	}
	
}

package com.cognizant.pad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pad.exception.ComponentTypeNotFoundException;
import com.cognizant.pad.exception.InvalidQuantityException;
import com.cognizant.pad.model.ComponentType;
import com.cognizant.pad.service.PackagingAndDeliveryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/packaging-and-delivery")
public class PackagingAndDeliveryController 
{
	@Autowired
	private PackagingAndDeliveryService packagingAndDeliveryService;
	
	@GetMapping("/packagingAndDeliveryService/{componentType}/{quantity}")
	public double getPackagingDeliveryCharge(@PathVariable ComponentType componentType,@PathVariable int quantity) throws ComponentTypeNotFoundException,InvalidQuantityException
	{
		log.info("PackaingAndDeliveryController :- getPackagingDeliveryCharge called");
		return packagingAndDeliveryService.getPackagingDeliveryCharge(componentType,quantity);
	}
}

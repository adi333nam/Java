package com.cognizant.pad.service;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.cognizant.pad.exception.ComponentTypeNotFoundException;
import com.cognizant.pad.exception.InvalidQuantityException;
import com.cognizant.pad.model.ComponentType;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class PackagingAndDeliveryService 
{

	private EnumMap<ComponentType,Integer> packagingItems=new EnumMap<>(ComponentType.class);
	private EnumMap<ComponentType,Integer> deliveryItems=new EnumMap<>(ComponentType.class);
	
	@PostConstruct
	public void init() {
		
		packagingItems.put(ComponentType.ACCESSORY, 50);
		packagingItems.put(ComponentType.INTEGRAL, 100);
		
		deliveryItems.put(ComponentType.ACCESSORY, 100);
		deliveryItems.put(ComponentType.INTEGRAL, 200);
		
	}

	public double getPackagingDeliveryCharge(ComponentType componentType, int quantity)
			throws ComponentTypeNotFoundException, InvalidQuantityException {
		
		log.info("PackaingAndDeliveryService :- getPackagingDeliveryCharge called");
		
		double packagingDaeliveryCharge = 0;

		if (quantity <= 0 || quantity > 100) {
			throw new InvalidQuantityException("Invalid quantity " + quantity);
		}
		
		packagingDaeliveryCharge = getPackagingCharge(componentType, quantity)
				+ getDeliveryCharge(componentType, quantity);

		log.info("PackaingAndDeliveryService :- getPackagingAndDeliveryCharge end");
		
		return packagingDaeliveryCharge;
	}

	
	public double getPackagingCharge(ComponentType componentType, int quantity) 
			throws ComponentTypeNotFoundException {

		log.info("PackaingAndDeliveryService :- getPackagingCharge called");

		double packagingCharge = 0;
		
		try {
			Optional<Entry<ComponentType, Integer>> optional = packagingItems.entrySet().stream().filter(x -> x.getKey().equals(componentType))
					.findAny();
			
			if(optional.isPresent())
			{
				packagingCharge=optional.get().getValue();
			}
			else
			{
				throw new NullPointerException();
			}
			
			
		} catch (NullPointerException e) {
			throw new ComponentTypeNotFoundException("Component Does Not Exit " + componentType);
		}
		
		packagingCharge *= quantity;

		log.info("PackaingAndDeliveryService :- getPackagingCharge end");
		return packagingCharge;
	}

	public double getDeliveryCharge(ComponentType componentType, int quantity) 
			throws ComponentTypeNotFoundException {

		log.info("PackaingAndDeliveryService :- getDeliveryCharge called");

		double deliveryCharge = 0;
		try {
			Optional<Entry<ComponentType, Integer>> optional = deliveryItems.entrySet().stream().filter(x -> x.getKey().equals(componentType))
					.findAny();
			if(optional.isPresent())
			{
				deliveryCharge=optional.get().getValue();
			}
			else
			{
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new ComponentTypeNotFoundException("Component Does Not Exit " + componentType.toString());
		}
		
		deliveryCharge *= quantity;

		log.info("PackaingAndDeliveryService :- getDeliveryCharge end");
		return deliveryCharge;
	}

}

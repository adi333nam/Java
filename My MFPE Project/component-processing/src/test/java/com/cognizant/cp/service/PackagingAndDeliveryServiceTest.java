package com.cognizant.cp.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.cp.exception.ComponentTypeNotFoundException;
import com.cognizant.cp.exception.InvalidQuantityException;
import com.cognizant.cp.model.ComponentType;

@ExtendWith(MockitoExtension.class)
class PackagingAndDeliveryServiceTest {

	@Mock
	private PackagingAndDeliveryService packagingAndDeliveryService;
	
	@Test
	void testgetPackagingAndDeliveryChargesForACCESSORY() {
		
		when(packagingAndDeliveryService.getPackagingAndDeliveryCharges(ComponentType.ACCESSORY,10)).thenReturn(1500.0);
		double charge=packagingAndDeliveryService.getPackagingAndDeliveryCharges(ComponentType.ACCESSORY,10);
		assertEquals(1500.0,charge);

	}
	
	@Test
	void testgetPackagingAndDeliveryChargesForINTEGRAL() {
		
		when(packagingAndDeliveryService.getPackagingAndDeliveryCharges(ComponentType.INTEGRAL,10)).thenReturn(3000.0);
		double charge=packagingAndDeliveryService.getPackagingAndDeliveryCharges(ComponentType.INTEGRAL,10);
		assertEquals(3000.0,charge);

	}
	
	@Test
	void testgetPackagingAndDeliveryChargesForComponentDoesNotExit() {
		
		when(packagingAndDeliveryService.getPackagingAndDeliveryCharges(ComponentType.PROTECTIVE_SHEALTH,10)).thenThrow(new ComponentTypeNotFoundException("Not Found"));
		Assertions.assertThrows(ComponentTypeNotFoundException.class,()->packagingAndDeliveryService.getPackagingAndDeliveryCharges(ComponentType.PROTECTIVE_SHEALTH,10));

	}
	
	@Test
	void testgetPackagingAndDeliveryChargesForInvalidQuantityException() {
		
		when(packagingAndDeliveryService.getPackagingAndDeliveryCharges(ComponentType.INTEGRAL,-10)).thenThrow(new InvalidQuantityException("Invalid Quantity -10"));
		Assertions.assertThrows(InvalidQuantityException.class,()->packagingAndDeliveryService.getPackagingAndDeliveryCharges(ComponentType.INTEGRAL,-10));

	}
}


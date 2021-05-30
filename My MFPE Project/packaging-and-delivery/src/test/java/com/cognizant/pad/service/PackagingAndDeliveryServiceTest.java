package com.cognizant.pad.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.pad.exception.ComponentTypeNotFoundException;
import com.cognizant.pad.exception.InvalidQuantityException;
import com.cognizant.pad.model.ComponentType;


@ExtendWith(MockitoExtension.class)
class PackagingAndDeliveryServiceTest {

	@Mock
	PackagingAndDeliveryService packagingAndServiceCharge;
	
	
	@Test
	void testGetProcessingDeliveryCharge() throws ComponentTypeNotFoundException, InvalidQuantityException
	{
		when(packagingAndServiceCharge.getPackagingDeliveryCharge(ComponentType.ACCESSORY,15)).thenReturn(2250.0);
		double actualProcessingCharge=packagingAndServiceCharge.getPackagingDeliveryCharge(ComponentType.ACCESSORY,15);
		assertEquals(2250.0,actualProcessingCharge);
	}
	
	@Test
	void  testGetProcessingDeliveryChargeForComponentTypeNotFoundException() throws ComponentTypeNotFoundException, InvalidQuantityException
	{
		
		when(packagingAndServiceCharge.getPackagingDeliveryCharge(ComponentType.PROTECTIVE_SHEALTH,10)).thenThrow(ComponentTypeNotFoundException.class);
		assertThrows(ComponentTypeNotFoundException.class,()->packagingAndServiceCharge.getPackagingDeliveryCharge(ComponentType.PROTECTIVE_SHEALTH,10));
		
	}
	
	@Test
	void  testGetProcessingDeliveryChargeForInvalidQuantityException() throws ComponentTypeNotFoundException, InvalidQuantityException
	{
		
		when(packagingAndServiceCharge.getPackagingDeliveryCharge(ComponentType.INTEGRAL,102)).thenThrow(InvalidQuantityException.class);
		assertThrows(InvalidQuantityException.class,()->packagingAndServiceCharge.getPackagingDeliveryCharge(ComponentType.INTEGRAL,102));
		
	}
	
	@Test
	void  testGetProcessingCharge() throws ComponentTypeNotFoundException
	{
		when(packagingAndServiceCharge.getPackagingCharge(ComponentType.ACCESSORY,15)).thenReturn(750.0);
		double actualProcessingCharge=packagingAndServiceCharge.getPackagingCharge(ComponentType.ACCESSORY,15);
		assertEquals(750.0,actualProcessingCharge);
	}
	
	@Test
	void  testGetProcessingChargeForComponentTypeNotFoundException() throws ComponentTypeNotFoundException
	{
		
		when(packagingAndServiceCharge.getPackagingCharge(ComponentType.PROTECTIVE_SHEALTH,10)).thenThrow(ComponentTypeNotFoundException.class);
		assertThrows(ComponentTypeNotFoundException.class,()->packagingAndServiceCharge.getPackagingCharge(ComponentType.PROTECTIVE_SHEALTH,10));
		
	}
	
	@Test
	void  testGetDeliveryCharge() throws ComponentTypeNotFoundException
	{
		when(packagingAndServiceCharge.getDeliveryCharge(ComponentType.INTEGRAL,15)).thenReturn(3000.0);
		double actualProcessingCharge=packagingAndServiceCharge.getDeliveryCharge(ComponentType.INTEGRAL,15);
		assertEquals(3000.0,actualProcessingCharge);
	}
	
	@Test
	void  testGetDeliveryChargeForComponentTypeNotFoundException() throws ComponentTypeNotFoundException
	{
		
		when(packagingAndServiceCharge.getDeliveryCharge(ComponentType.PROTECTIVE_SHEALTH,20)).thenThrow(ComponentTypeNotFoundException.class);
		assertThrows(ComponentTypeNotFoundException.class,()->packagingAndServiceCharge.getDeliveryCharge(ComponentType.PROTECTIVE_SHEALTH,20));
		
	}
}

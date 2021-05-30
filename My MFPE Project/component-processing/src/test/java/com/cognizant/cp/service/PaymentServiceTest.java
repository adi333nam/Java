package com.cognizant.cp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.cp.exception.InvalidCreditCardNumberException;
import com.cognizant.cp.exception.InvalidProcessingChargeException;


@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {


	@Mock
	private PaymentService paymentService;
	
	@Test
	 void getPaymentTest()
	{
		when(paymentService.getPayment(12345678903555l,10000,10000)).thenReturn(0.0);
		double currentBalance=paymentService.getPayment(12345678903555l,10000,10000);
		assertEquals(0.0,currentBalance);
	}
	

	
	@Test
	void getPaymentInvalidProcessingChargeExceptionTest()
	{
		when(paymentService.getPayment(12345678903555l,10000,20000)).thenThrow(new InvalidProcessingChargeException("credit limit is less then payment charge"));
		
		Assertions.assertThrows(InvalidProcessingChargeException.class,()->paymentService.getPayment(12345678903555l,10000,20000));

	}
	
	@Test
	void getInvalidCreditCardNumberExceptionTest()
	{
		when(paymentService.getPayment(123456789032l,10000,3000)).thenThrow(new InvalidCreditCardNumberException("credit card number is invalid"));
		
		Assertions.assertThrows(InvalidCreditCardNumberException.class,()->paymentService.getPayment(123456789032l,10000,3000));

	}

}

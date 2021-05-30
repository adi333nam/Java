package com.cognizant.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.payment.exception.InvalidCreditCardNumberException;
import com.cognizant.payment.exception.InvalidProcessingChargeException;
import com.cognizant.payment.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/getPayment/{creditCardNumber}/{creditLimit}/{processingCharge}")
	public double processPayment(@PathVariable long creditCardNumber,@PathVariable double creditLimit,@PathVariable double processingCharge) throws InvalidProcessingChargeException, InvalidCreditCardNumberException
	{
		log.info("Payment Controller :- processPayemnt called");
		return paymentService.processPayment(creditCardNumber,creditLimit,processingCharge);
	}
}

package com.cognizant.payment.exception;

public class InvalidCreditCardNumberException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCreditCardNumberException()
	{
		super();
	}
	
	public InvalidCreditCardNumberException(String message)
	{
		super(message);
	}
}

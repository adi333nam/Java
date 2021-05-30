package com.cognizant.cp.exception;

public class InvalidCreditCardNumberException extends RuntimeException{

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

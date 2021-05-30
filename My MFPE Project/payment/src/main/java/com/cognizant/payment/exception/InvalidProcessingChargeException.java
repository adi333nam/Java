package com.cognizant.payment.exception;

public class InvalidProcessingChargeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public InvalidProcessingChargeException()
	{
		super();
	}
	

	public InvalidProcessingChargeException(String message) {
		super(message);
	}
	
	
}

package com.cognizant.cp.exception;
public class InvalidProcessingChargeException extends RuntimeException{
	
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

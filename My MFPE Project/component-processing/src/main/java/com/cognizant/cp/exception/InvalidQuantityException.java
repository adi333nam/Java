package com.cognizant.cp.exception;

public class InvalidQuantityException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidQuantityException(String message)
	{
		super(message);
	}
	
	public InvalidQuantityException()
	{
		super();
	}
}

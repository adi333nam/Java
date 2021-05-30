package com.cognizant.cp.exception;

public class RequestIdNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestIdNotFoundException(String message)
	{
		super(message);
	}
	public RequestIdNotFoundException()
	{
		super();
	}

}

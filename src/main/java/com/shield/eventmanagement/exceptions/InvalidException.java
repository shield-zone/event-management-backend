package com.shield.eventmanagement.exceptions;

public class InvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidException()
	{
		super("Not Found!!");
	}
	
	public InvalidException(String message)
	{
		super(message);
	}
}

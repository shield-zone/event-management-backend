package com.shield.eventmanagement.exceptions.organizer;

public class OrganizerNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrganizerNotFoundException()
	{
		super("Not Found!!");
	}
	
	public OrganizerNotFoundException(String message)
	{
		super(message);
	}
	
}

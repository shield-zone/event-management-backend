package com.shield.eventmanagement.exceptions.user;

public class UserNotFoundException extends Exception {
	
	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
		super("User Not found");
	}
	
	public UserNotFoundException(String message)
	{
		super(message);
	}

}

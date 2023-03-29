package com.shield.eventmanagement.exceptions.location;

public class LocationNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocationNotFoundException() {
		// TODO Auto-generated constructor stub
		super("Not able to find the location");
	}

	public LocationNotFoundException(String message) {
		super(message);
	}
}

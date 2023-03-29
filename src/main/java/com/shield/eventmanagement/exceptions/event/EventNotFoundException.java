package com.shield.eventmanagement.exceptions.event;

public class EventNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventNotFoundException() {
		super("Event Not Found");
	}

	public EventNotFoundException(String message) {
		super(message);
	}

}

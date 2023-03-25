package com.shield.eventmanagement.exceptions.attendee;

public class AttendeeException extends Exception {
    private static final Long serialVersionUID = 1L;

    public AttendeeException() {
        super("Exception on Attendee");
    }

    public AttendeeException(String message) {
        super(message);
    }
}

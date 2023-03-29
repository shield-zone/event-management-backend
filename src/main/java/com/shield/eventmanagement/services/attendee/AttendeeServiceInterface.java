package com.shield.eventmanagement.services.attendee;

import com.shield.eventmanagement.entities.Attendee;

import java.util.Optional;

public interface AttendeeServiceInterface {
    Optional<Attendee> insertAttendee(Attendee attendee);
    Optional<Attendee> updateAttendee(Attendee attendee);
}

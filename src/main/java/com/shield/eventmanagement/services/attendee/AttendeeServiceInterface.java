package com.shield.eventmanagement.services.attendee;

import com.shield.eventmanagement.entities.Attendee;

import java.util.List;
import java.util.Optional;

public interface AttendeeServiceInterface {
    List<Attendee> findByEmail(String email);
    List<Attendee> findByName(String name);
    Optional<Attendee> insertAttendee(Attendee attendee);
    Optional<Attendee> updateAttendee(Attendee attendee);
}

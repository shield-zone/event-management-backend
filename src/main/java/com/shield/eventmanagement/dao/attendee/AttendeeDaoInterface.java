package com.shield.eventmanagement.dao.attendee;

import com.shield.eventmanagement.entities.Attendee;

import java.util.Optional;

public interface AttendeeDaoInterface {
    Optional<Attendee> insertAttendee(Attendee attendee);
    boolean doAttendeeExists(Attendee attendee);
}

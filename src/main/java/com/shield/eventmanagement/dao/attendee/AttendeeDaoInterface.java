package com.shield.eventmanagement.dao.attendee;

import com.shield.eventmanagement.entities.Attendee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AttendeeDaoInterface {
    List<Attendee> findByEmail(String email);
    List<Attendee> findByName(String name);
    Optional<Attendee> findAttendeeByEmailAndAttendeeId(String email, Long eventId);
    Optional<Attendee> insertAttendee(Attendee attendee);
}

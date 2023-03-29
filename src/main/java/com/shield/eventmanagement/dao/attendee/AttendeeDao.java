package com.shield.eventmanagement.dao.attendee;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.repositories.attendee.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class AttendeeDao implements AttendeeDaoInterface {

    @Autowired
    AttendeeRepository repository;

    public Optional<Attendee> insertAttendee(Attendee attendee) {
        if (doAttendeeExists(attendee)) return Optional.empty();
        return Optional.of(repository.saveAndFlush(attendee));
    }

    public boolean doAttendeeExists(Attendee attendee) {
        Optional<Attendee> tempAttendeeOptional = repository.findById(attendee.getAttendeeId());

        if (!tempAttendeeOptional.isPresent())
            return false;

        Attendee tempAttendee = tempAttendeeOptional.get();

        return tempAttendee.getAttendeeId() == attendee.getAttendeeId();
    }
}

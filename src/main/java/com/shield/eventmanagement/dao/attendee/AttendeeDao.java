package com.shield.eventmanagement.dao.attendee;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.repositories.attendee.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class AttendeeDao implements AttendeeDaoInterface {

    @Autowired
    AttendeeRepository repository;

    public List<Attendee> findByEmail(String email) {
        return repository.findByEmailContainingIgnoreCase(email);
    }

    public List<Attendee> findByName(String name) {
        return repository.findAttendeeByNameContainingIgnoreCase(name);
    }

    public Optional<Attendee> findAttendeeByEmailAndAttendeeId(String email, Long eventId){
        return repository.findAttendeeByEmailAndAttendeeId(email, eventId);
    }

    public Optional<Attendee> insertAttendee(Attendee attendee) {
        if (doAttendeeExists(attendee)) return Optional.empty();
        return Optional.of(repository.saveAndFlush(attendee));
    }

    private boolean doAttendeeExists(Attendee attendee) {
        Optional<Attendee> tempAttendeeOptional = repository
                .findAttendeeByEmailAndAttendeeId(
                        attendee.getEmail(),
                        attendee.getEvent().get(0).getEventId()
                );

        if (!tempAttendeeOptional.isPresent())
            return false;

        Attendee tempAttendee = tempAttendeeOptional.get();

        return tempAttendee.getAttendeeId() == attendee.getAttendeeId();
    }
}

package com.shield.eventmanagement.services;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.repositories.AttendeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeService {

    @Autowired
    AttendeeRepository repository;

    public Optional<Attendee> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Attendee> getAttendeesByName(String name) {
        return repository.getAttendeesByNameContainingIgnoreCase(name);
    }

    public List<Attendee> getAttendeesByLocation(String location) {
        return repository.getAttendeesByLocationContainingIgnoreCase(location);
    }

    public List<Attendee> getAttendeesByEventName(String eventName) {
        return repository.getAttendeesByEventNameContainingIgnoreCase(eventName);
    }

    public List<Attendee> getCancelledAttendeesByEventName(String eventName) {
        return repository
                .getCancelledAttendeesByEventNameContainingIgnoreCase(eventName)
                .stream()
                .filter(Attendee::isCancelledRegistration)
                .collect(Collectors.toList());
    }

    public Attendee insertAttendee(Attendee attendee) {
        return repository.saveAndFlush(attendee);
    }

    public Optional<Attendee> updateAttendee(Attendee attendee) {
        Optional<Attendee> tempAttendee = repository.findByEmail(attendee.getEmail());
        if (!tempAttendee.isPresent()) {
            return Optional.empty();
        }

        if (tempAttendee.get().getAttendeeId() != attendee.getAttendeeId()) {
            return Optional.empty();
        }

        return Optional.of(repository.saveAndFlush(attendee));
    }
}

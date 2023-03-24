package com.shield.eventmanagement.services.attendee;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.repositories.attendee.AttendeeRepository;
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

    public List<Attendee> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Attendee> findByEmailAndEventName(String email, String eventName) {
        return repository.findAttendeeByEmailAndEventName(email, eventName);
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

    public Optional<Attendee> cancelRegistrationByEventName(Attendee attendee) {
        if (!doAttendeeExists(attendee)) return Optional.empty();

        attendee.setCancelledRegistration(true);
        return Optional.of(repository.saveAndFlush(attendee));
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
        if (!doAttendeeExists(attendee)) return Optional.empty();

        return Optional.of(repository.saveAndFlush(attendee));
    }

    private boolean doAttendeeExists(Attendee attendee) {
        Optional<Attendee> tempAttendeeOptional = repository
                .findAttendeeByEmailAndEventId(
                        attendee.getEmail(),
                        attendee.getEvent().get(0).getEventId()
                );

        if (!tempAttendeeOptional.isPresent())
            return false;

        Attendee tempAttendee = tempAttendeeOptional.get();

        if (tempAttendee.getAttendeeId() != attendee.getAttendeeId())
            return false;
        return true;
    }
}

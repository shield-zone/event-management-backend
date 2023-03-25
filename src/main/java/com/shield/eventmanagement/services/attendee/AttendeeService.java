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
        return repository.findByEmailContainingIgnoreCase(email);
    }

    public List<Attendee> findByName(String name) {
        return repository.findAttendeeByNameContainingIgnoreCase(name);
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

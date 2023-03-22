package com.shield.eventmanagement.services;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.repositories.AttendeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return repository.getAttendeesByName(name);
    }

    public List<Attendee> getAttendeesByLocation(String location) {
        return repository.getAttendeesByLocation(location);
    }

    public List<Attendee> getAttendeesByEventName(String eventName) {
        return repository.getAttendeesByEventName(eventName);
    }
}

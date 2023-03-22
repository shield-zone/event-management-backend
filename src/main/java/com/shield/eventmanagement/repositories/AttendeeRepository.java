package com.shield.eventmanagement.repositories;

import com.shield.eventmanagement.entities.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    Optional<Attendee> findByEmail(String email);
    List<Attendee> getAttendeesByName(String name);
    List<Attendee> getAttendeesByLocation(String location);
    List<Attendee> getAttendeesByEventName(String eventName);
}

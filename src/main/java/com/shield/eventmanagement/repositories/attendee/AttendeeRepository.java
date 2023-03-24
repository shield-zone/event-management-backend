package com.shield.eventmanagement.repositories.attendee;

import com.shield.eventmanagement.entities.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    List<Attendee> findByEmail(String email);
    Optional<Attendee> findAttendeeByEmailAndEventId(String email, long eventId);
    List<Attendee> findAttendeeByEmailAndEventName(String email, String eventName);
    List<Attendee> getAttendeesByNameContainingIgnoreCase(String name);
    List<Attendee> getAttendeesByLocationContainingIgnoreCase(String location);
    List<Attendee> getAttendeesByEventNameContainingIgnoreCase(String eventName);
    List<Attendee> getCancelledAttendeesByEventNameContainingIgnoreCase(String eventName);
}

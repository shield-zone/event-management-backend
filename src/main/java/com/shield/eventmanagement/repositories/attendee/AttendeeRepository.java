package com.shield.eventmanagement.repositories.attendee;

import com.shield.eventmanagement.entities.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
//    String findAttendeeByEmailAndEventIdQuery = "SELECT a.* from attendee a, event e where a.event_id = e.event_id AND e.event_id=:eventId AND a.email=:email";
//    String findAttendeeByEmailAndEventNameQuery = "SELECT a.* FROM attendee a, event e where a.event_id = e.event_id AND e.event_name=:eventName AND a.email=:email";
//    String getAttendeesByLocationContainingIgnoreCaseQuery = "SELECT a.* FROM attendee a, event e, location l where l.location_name like %:location% AND e.location_id = l.location_id AND a.event_id = e.event_id";
//    String getAttendeesByEventNameContainingIgnoreCaseQuery = "SELECT a.* FROM attendee a, event e where e.event_name LIKE %:eventName% AND a.event_id = e.event_id";
//    String getCancelledAttendeesByEventNameContainingIgnoreCaseQuery = "SELECT a.* from attendee a, event e where e.event_name LIKE %:eventName% AND a.cancelled_registration = 1 AND e.event_id = a.event_id";
//    @Query(value = findAttendeeByEmailAndEventIdQuery, nativeQuery = true)
//    Optional<Attendee> findAttendeeByEmailAndEventId(String email, long eventId);
//
//    @Query(value = findAttendeeByEmailAndEventNameQuery, nativeQuery = true)
//    List<Attendee> findAttendeeByEmailAndEventName(String email, String eventName);
//
//    List<Attendee> getAttendeesByNameContainingIgnoreCase(String name);
//
//    @Query(value = getAttendeesByLocationContainingIgnoreCaseQuery, nativeQuery = true)
//    List<Attendee> getAttendeesByLocationContainingIgnoreCase(String location);
//
//    @Query(value = getAttendeesByEventNameContainingIgnoreCaseQuery, nativeQuery = true)
//    List<Attendee> getAttendeesByEventNameContainingIgnoreCase(String eventName);
//
//    @Query(value = getCancelledAttendeesByEventNameContainingIgnoreCaseQuery, nativeQuery = true)
//    List<Attendee> getCancelledAttendeesByEventNameContainingIgnoreCase(String eventName);

    List<Attendee> findByEmailContainingIgnoreCase(String email);
    List<Attendee> findAttendeeByNameContainingIgnoreCase(String name);

    Optional<Attendee> findAttendeeByEmailAndAttendeeId(String email, Long id);
}

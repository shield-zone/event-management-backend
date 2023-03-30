package com.shield.eventmanagement.repositories.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	final String getAttendeeByEventIdQuery = "Select * from events e, attendee_event a where a.event_event_id = :id and e.event_id = a.event_event_id";
	List<Attendee> getAttendeeByEventId(Long eventId);
	@Query(value = getAttendeeByEventIdQuery, nativeQuery = true)
	List<Event> getEventsByAttendees(@Param("id") Long id);
}

package com.shield.eventmanagement.repositories.event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	final String getAttendeeByEventIdQuery = "Select * from events e, attendee_event a where a.event_event_id = :id and e.event_id = a.event_event_id";

	Optional<Event> findByEventId(Long eventId);
	List<Event> getEventsByEventNameContainingIgnoreCase(String eventName);
	List<Event> getEventsByEventTypeContainingIgnoreCase(String eventType);
	List<Event> getEventsByEventPrice(Double eventPrice);
	List<Event> getEventByStartDateContainingIgnoreCase(String startDate);
	List<Attendee> getAttendeeByEventId(Long eventId);
	@Query(value = getAttendeeByEventIdQuery, nativeQuery = true)
	List<Event> getEventsByAttendees(@Param("id") Long id);
}

package com.shield.eventmanagement.repositories.event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shield.eventmanagement.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	Optional<Event> findByEventId(Long eventId);
	List<Event> getEventsByNameContainingIgnoreCase(String eventName);
	List<Event> getEventsByEventTypeContainingIgnoreCase(String eventType);
	List<Event> getEventsByEventPriceContainingIgnoreCase(Double eventPrice);
	List<Event> getEventByStartDateContainingIgnoreCase(String startDate);
}

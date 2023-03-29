package com.shield.eventmanagement.services.event;

import java.util.List;
import java.util.Optional;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.request.event.EventUpdateRequest;

public interface EventService {
	
    Event create(Event event);
	
    Event update(EventUpdateRequest eventUpdateRequest);
	
	Optional<Event> findByEventId(Long eventId);
	
	List<Attendee> getAttendeeByEventId(Long eventId);
	
    List<Event> fetchAllEvents();
	
	Event deleteEvent(Long eventId);
	
    List<Event> getEventsByAttendeeId(Long id);
}

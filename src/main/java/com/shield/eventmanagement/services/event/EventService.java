package com.shield.eventmanagement.services.event;

import java.util.List;
import java.util.Optional;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.exceptions.event.EventNotFoundException;
import com.shield.eventmanagement.request.event.EventUpdateRequest;

public interface EventService {
	
    Event create(Event event);
	
    Event update(EventUpdateRequest eventUpdateRequest) throws EventNotFoundException;
	
	Optional<Event> findByEventId(Long eventId) throws EventNotFoundException;
	
	List<Attendee> getAttendeeByEventId(Long eventId) throws EventNotFoundException;
	
    List<Event> fetchAllEvents();
	
	Event deleteEvent(Long eventId) throws EventNotFoundException;
	
    List<Event> getEventsByAttendeeId(Long id);
}

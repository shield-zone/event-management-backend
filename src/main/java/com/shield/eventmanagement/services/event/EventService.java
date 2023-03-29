package com.shield.eventmanagement.services.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.repositories.event.EventRepository;
import com.shield.eventmanagement.request.event.EventUpdateRequest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EventService {
	
	@Autowired
	EventRepository repository;

	
	public Event create(Event event)
	{
		event = repository.save(event);
		return event;
	}
	
	public Event update(EventUpdateRequest eventUpdateRequest)
	{
		Optional<Event> eventOptional = findByEventId(eventUpdateRequest.getEventId());
		Event event = eventOptional.get();
		
		if(eventUpdateRequest.getEndDate()!=null && !eventUpdateRequest.getEndDate().isEmpty())
		{
			event.setEndDate(eventUpdateRequest.getEndDate());
		}
		
		if(eventUpdateRequest.getEventName()!=null && !eventUpdateRequest.getEventName().isEmpty())
		{
			event.setEventName(eventUpdateRequest.getEventName());
		}
		
		if(eventUpdateRequest.getStartDate()!=null && !eventUpdateRequest.getStartDate().isEmpty())
		{
			event.setStartDate(eventUpdateRequest.getStartDate());
		}
		
		if(eventUpdateRequest.getEventPrice()!=null && eventUpdateRequest.getEventPrice()!=0)
		{
			event.setEventPrice(eventUpdateRequest.getEventPrice());
		}
		
		event = repository.save(event);
		
		return event;
	}
	
	public Optional<Event> findByEventId(Long eventId) {
		return repository.findByEventId(eventId);
	}
	
	
	public List<Event> getEventsByName(String eventName) {
		return repository.getEventsByEventNameContainingIgnoreCase(eventName);
	}
	
	public List<Event> getEventsByEventType(String eventType) {
		return repository.getEventsByEventTypeContainingIgnoreCase(eventType);
	}
	
	public List<Event> getEventsByEventPrice(Double eventPrice) {
		return repository.getEventsByEventPrice(eventPrice);
	}
	
	public List<Event> getEventByStartDate(String startDate) {
		return repository.getEventByStartDateContainingIgnoreCase(startDate);
	}
	
	public List<Attendee> getAttendeeByEventId(Long eventId) {
		List<Attendee> attendees = new ArrayList<Attendee>();
		Event event = findByEventId(eventId).get();
		attendees.addAll(event.getAttendees());
		return attendees;
	}
	
	public List<Event> fetchAllEvents()
	{
		List<Event> events = repository.findAll();
		return events;
	}
	
	public Event deleteEvent(Long eventId){
		
		Optional<Event> eventOptional = findByEventId(eventId);
		Event event = eventOptional.get();
		event.setDeleted(true);
		
		return event;
	}

}

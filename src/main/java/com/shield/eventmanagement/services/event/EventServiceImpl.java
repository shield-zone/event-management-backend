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

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository repository;

	
	@Override
	public Event create(Event event)
	{
		event = repository.save(event);
		return event;
	}
	
	@Override
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
	
	@Override
	public Optional<Event> findByEventId(Long eventId) {
		return repository.findById(eventId);
	}
	
	@Override
	public List<Attendee> getAttendeeByEventId(Long eventId) {
		List<Attendee> attendees = new ArrayList<Attendee>();
		Event event = findByEventId(eventId).get();
		attendees.addAll(event.getAttendees());
		return attendees;
	}
	
	@Override
	public List<Event> fetchAllEvents()
	{
		List<Event> events = repository.findAll();
		return events;
	}
	
	@Override
	public Event deleteEvent(Long eventId){
		
		Optional<Event> eventOptional = findByEventId(eventId);
		Event event = eventOptional.get();
		event.setDeleted(true);
		
		return event;
	}
}

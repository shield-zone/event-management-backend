package com.shield.eventmanagement.services.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.dao.attendee.AttendeeDao;
import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.exceptions.event.EventNotFoundException;
import com.shield.eventmanagement.repositories.event.EventRepository;
import com.shield.eventmanagement.request.event.EventUpdateRequest;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository repository;

	@Autowired
	AttendeeDao attendeeDao;
	
	@Override
	public Event create(Event event)
	{
		event = repository.save(event);
		return event;
	}
	
	@Override
	public Event update(EventUpdateRequest eventUpdateRequest) throws EventNotFoundException
	{
		Optional<Event> eventOptional = findByEventId(eventUpdateRequest.getEventId());
		
		if(!eventOptional.isPresent())
		{
			throw new EventNotFoundException("Invalid event Id can't proceed to update");
		}
		
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
	public Optional<Event> findByEventId(Long eventId) throws EventNotFoundException {
		
		Optional<Event> eventOptional = repository.findById(eventId);
		
		if(!eventOptional.isPresent())
		{
			throw new EventNotFoundException("Invalid event Id can't proceed to update");
		}
		return eventOptional;
	}
	
	@Override
	public List<Attendee> getAttendeeByEventId(Long eventId) throws EventNotFoundException {
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
	public Event deleteEvent(Long eventId) throws EventNotFoundException{
		
		Optional<Event> eventOptional = findByEventId(eventId);
		Event event = eventOptional.get();
		event.setDeleted(true);
		event = repository.save(event);
		return event;
	}
	
	@Override
	public List<Event> getEventsByAttendeeId(Long id) {
		Attendee attendee = Attendee.builder().attendeeId(id).build();
		boolean doesAttendeeExist = attendeeDao.doAttendeeExists(attendee);

		if (!doesAttendeeExist)
			return Collections.emptyList();

		return repository.getEventsByAttendees(id);
	}
}

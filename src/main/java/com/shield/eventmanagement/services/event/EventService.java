package com.shield.eventmanagement.services.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.repositories.event.EventRepository;

import com.shield.eventmanagement.entities.Event;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EventService {
	
	@Autowired
	EventRepository repository;
	
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

}

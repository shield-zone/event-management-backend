package com.shield.eventmanagement.restcontrollers.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.services.event.EventService;

@RestController
@RequestMapping("/api/v1/event")
public class EventRestController {
	
	@Autowired
	EventService service;
	
	@GetMapping("find-by-event-id/{eventId}")
	Optional<Event> findByEventId(@PathVariable Long eventId) {
		return service.findByEventId(eventId);
	}
	
	@GetMapping("find-by-event-name/{eventName}") 
	List<Event> getEventsByName(@PathVariable String eventName) {
		return service.getEventsByName(eventName);
	}
	
	@GetMapping("find-by-event-type/{eventType}")
	List<Event> getEventsByEventType(@PathVariable String eventType) {
		return service.getEventsByEventType(eventType);
	}
	
	@GetMapping("find-by-event-price/{eventPrice}")
	List<Event> getEventsByEventPrice(@PathVariable Double eventPrice) {
		return service.getEventsByEventPrice(eventPrice);
	}
	
	@GetMapping("find-by-event-start-date/{startDate}")
	List<Event> getEventsByStartDate(@PathVariable String startDate) {
		return service.getEventByStartDate(startDate);
	}
}

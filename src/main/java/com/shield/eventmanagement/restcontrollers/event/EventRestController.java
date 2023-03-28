package com.shield.eventmanagement.restcontrollers.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.services.event.EventService;

@RestController
@RequestMapping("/api/v1/event")
public class EventRestController {
	
	@Autowired
	EventService service;
	
	@PostMapping("/create-user")
	public ResponseEntity<?> create(@RequestBody Event event)
	{
		event = service.create(event);
		
		return new ResponseEntity<>(event, HttpStatus.OK);
	}
	
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
	
	@GetMapping("find-attendee-by-event-id/{eventId}")
	List<Attendee> getAttendeeByEventId(@PathVariable Long eventId) {
		return service.getAttendeeByEventId(eventId);
	}
}

package com.shield.eventmanagement.restcontrollers.event;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.entities.Location;
import com.shield.eventmanagement.entities.Organizer;
import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.exceptions.event.EventNotFoundException;
import com.shield.eventmanagement.exceptions.location.LocationNotFoundException;
import com.shield.eventmanagement.exceptions.organizer.OrganizerNotFoundException;
import com.shield.eventmanagement.exceptions.user.UserNotFoundException;
import com.shield.eventmanagement.repositories.organizer.OrganizerRepository;
import com.shield.eventmanagement.request.event.EventOrganizerLocationRequest;
import com.shield.eventmanagement.request.event.EventRequest;
import com.shield.eventmanagement.request.event.EventUpdateRequest;
import com.shield.eventmanagement.services.event.EventService;
import com.shield.eventmanagement.services.location.LocationService;
import com.shield.eventmanagement.services.organizer.OrganizerService;
import com.shield.eventmanagement.services.user.UserService;

@RestController
@RequestMapping("/api/v1/event")
public class EventRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrganizerRepository organizerRepository;
	
	@Autowired
	EventService service;

	@Autowired
	LocationService locationService;

	@Autowired
	OrganizerService organizerService;
	
	@PostMapping("/create-event")
	public ResponseEntity<?> create(@Valid@RequestBody EventRequest eventReq) throws OrganizerNotFoundException, LocationNotFoundException
	{
		Optional<Location> locationOptional = locationService.findByLocationId(eventReq.getLocationId());
		Optional<Organizer> organizerOptional = organizerService.fetchById(eventReq.getOrganizerId());
		
		if (!locationOptional.isPresent() || !organizerOptional.isPresent())
			return ResponseEntity.status(404).body(null);

		Event event = Event.builder()
				.endDate(eventReq.getEndDate())
				.eventName(eventReq.getEventName())
				.eventPrice(eventReq.getEventPrice())
				.startDate(eventReq.getStartDate())
				.location(locationOptional.get())
				.organizer(organizerOptional.get())
				.attendees(Collections.emptyList())
				.build();

		event = service.create(event);
		
		return new ResponseEntity<>(event, HttpStatus.OK);
	}

	@PostMapping("create-event-organizer-location")
	public ResponseEntity<?> createEventOrganizerLocation(@Valid@RequestBody EventOrganizerLocationRequest request) throws UserNotFoundException {
		Location location = Location.builder()
				.address(request.getAddress())
				.country(request.getCountry())
				.locationName(request.getLocationName())
				.pincode(request.getPincode())
				.state(request.getState())
				.build();

		Optional<User> userOptional = userService.fetchById(request.getUserId());
		
		if(!userOptional.isPresent())
		{
			throw new UserNotFoundException();
		}
		
		User user = userOptional.get();
		Organizer organizer = null;
		if(!organizerRepository.findById(user.getUserId()).isPresent())
		{
				organizer = Organizer.builder()
				.organizerId(user.getUserId())
				.organizerName(user.getName())
				.emailId(user.getUserName())
				.isDeleted(false)
				.phoneNumber(request.getPhoneNumber())
				.presentSince(request.getPresentSince())
				.rating(request.getRating())
				.website(request.getWebsite())
				.build();
		}
		else
		{
			organizer = organizerRepository.findById(user.getUserId()).get();
		}

		Event event = Event.builder()
				.endDate(request.getEndDate())
				.isDeleted(false)
				.eventName(request.getEventName())
				.eventPrice(request.getEventPrice())
				.startDate(request.getStartDate())
				.location(location)
				.organizer(organizer)
				.attendees(Collections.emptyList())
				.build();

		event = service.create(event);

		return new ResponseEntity<>(event, HttpStatus.OK);
	}
	
	@PutMapping("/update-event")
	public ResponseEntity<?> updateEvent(@Valid@RequestBody EventUpdateRequest eventUpdateRequest) throws EventNotFoundException
	{
		Event event = service.update(eventUpdateRequest);
		
		return new ResponseEntity<>(event, HttpStatus.OK);
	}
	
	@GetMapping("find-by-event-id/{eventId}")
	Optional<Event> findByEventId(@PathVariable Long eventId) throws EventNotFoundException {
		return service.findByEventId(eventId);
	}
	
	@DeleteMapping("/delete-by-id/{eventId}")
	public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) throws EventNotFoundException
	{
		Event event = service.deleteEvent(eventId);
		
		return new ResponseEntity<>(event, HttpStatus.OK);
	}
	
	@GetMapping("/fetch-all-events")
	public ResponseEntity<?> fetchAll()
	{
		List<Event> events = service.fetchAllEvents();

		return new ResponseEntity<>(events, HttpStatus.OK);
	}

	@GetMapping("find-attendee-by-event-id/{eventId}")
	List<Attendee> getAttendeeByEventId(@PathVariable Long eventId) throws EventNotFoundException {
		return service.getAttendeeByEventId(eventId);
	}

	@GetMapping("get-events-by-attendee-id/{id}")
	public List<Event> getEventsByAttendeeId(@PathVariable Long id) {
		return service.getEventsByAttendeeId(id);
	}
}

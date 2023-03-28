package com.shield.eventmanagement.restcontrollers.organizer;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.entities.Organizer;
import com.shield.eventmanagement.exceptions.InvalidException;
import com.shield.eventmanagement.exceptions.organizer.OrganizerNotFoundException;
import com.shield.eventmanagement.exceptions.user.UserNotFoundException;
import com.shield.eventmanagement.request.organizer.OrganizerRequest;
import com.shield.eventmanagement.request.organizer.OrganizerUpdateRequest;
import com.shield.eventmanagement.services.organizer.OrganizerService;

@RestController
@RequestMapping("/api/v1/organizer")
public class OrganizerController {

	@Autowired
	private OrganizerService organizerService;

	@PostMapping("/create-organizer")
	public ResponseEntity<?> createOrganizer(@Valid @RequestBody OrganizerRequest organizerRequest) throws InvalidException, UserNotFoundException {

		Organizer organizer = organizerService.create(organizerRequest);
		return new ResponseEntity<>(organizer, HttpStatus.OK);
	}
	
	@PostMapping("/update-organizer")
	public ResponseEntity<?> updateOrganizer(@Valid @RequestBody OrganizerUpdateRequest organizerUpdateRequest)
	{
		Organizer organizer = organizerService.update(organizerUpdateRequest);
		
		return new ResponseEntity<>(organizer, HttpStatus.OK);
	}

	@GetMapping("/fetch-by-id/{id}")
	public ResponseEntity<?> fetchById(@PathVariable Long id) throws OrganizerNotFoundException {

		Optional<Organizer> organizer = organizerService.fetchById(id);

		return new ResponseEntity<>(organizer, HttpStatus.OK);
	}

	@GetMapping("/fetch-by-username")
	public ResponseEntity<?> fetchByUsername(@RequestParam("username") String username) throws OrganizerNotFoundException {
		Organizer organizer = organizerService.fetchByUsername(username);

		return new ResponseEntity<>(organizer, HttpStatus.OK);
	}

	@GetMapping("/fetch-by-rating")
	public ResponseEntity<?> fetByRating(@RequestParam("rating") String rating) {
		List<Organizer> organizersList = organizerService.fetchByRating(rating);

		return new ResponseEntity<>(organizersList, HttpStatus.OK);
	}

	@GetMapping("/fetch-by-website")
	public ResponseEntity<?> fetchByWebsite(@RequestParam("website") String website) throws OrganizerNotFoundException {
		Organizer organizer = organizerService.fetchByWebsite(website);

		return new ResponseEntity<>(organizer, HttpStatus.OK);
	}

	@GetMapping("/delete-by-id")
	public ResponseEntity<?> deleteById(@RequestParam("id") Long organizerId) throws OrganizerNotFoundException {
		String message = organizerService.delete(organizerId);

		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@GetMapping("/fetch-all")
	public ResponseEntity<?> fetchAll() {

		List<Organizer> organizerList = organizerService.fetchAll();

		return new ResponseEntity<>(organizerList, HttpStatus.OK);
	}
	
	@GetMapping("/fetch-all-events")
	public ResponseEntity<?> fetchAllEvents(){
		List<Event> eventList = organizerService.fetchAllEvent();
		
		return new ResponseEntity<>(eventList, HttpStatus.OK);
	}

}

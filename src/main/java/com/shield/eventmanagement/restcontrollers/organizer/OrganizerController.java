package com.shield.eventmanagement.restcontrollers.organizer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.request.organizer.OrganizerRequest;

@RestController
@RequestMapping("/api/v1/organizer")
public class OrganizerController {

	
	@PostMapping("/create-organizer")
	public ResponseEntity<?> createOrganizer(@RequestBody OrganizerRequest organizerRequest)
	{
		
		return new ResponseEntity<>(organizerRequest, HttpStatus.OK);
	}
	
	
}

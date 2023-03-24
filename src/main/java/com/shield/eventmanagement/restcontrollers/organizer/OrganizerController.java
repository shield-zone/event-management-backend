package com.shield.eventmanagement.restcontrollers.organizer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.entities.Organizer;
import com.shield.eventmanagement.exceptions.organizer.OrganizerNotFoundException;
import com.shield.eventmanagement.request.organizer.OrganizerRequest;
import com.shield.eventmanagement.services.organizer.OrganizerService;

@RestController
@RequestMapping("/api/v1/organizer")
public class OrganizerController {

	@Autowired
	private OrganizerService organizerService;

	@PostMapping("/create-organizer")
	public ResponseEntity<?> createOrganizer(@Valid @RequestBody OrganizerRequest organizerRequest) {

		return new ResponseEntity<>(organizerRequest, HttpStatus.OK);
	}

	@GetMapping("/fetch-by-id")
	public ResponseEntity<?> fetchById(@RequestBody Organizer requestOrganizer) {
		Organizer organizer = null;
		try {
			organizer = organizerService.fetchById(requestOrganizer.getOrganizerId());
		} catch (OrganizerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(organizer, HttpStatus.OK);
	}

	@GetMapping("/fetch-by-username")
	public ResponseEntity<?> fetchByUsername(@RequestParam("username") String username) {
		Organizer organizer = null;
		try {
			organizer = organizerService.fetchByUsername(username);
		} catch (OrganizerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(organizer, HttpStatus.OK);
	}

	@GetMapping("/fetch-by-rating")
	public ResponseEntity<?> fetByRating(@RequestParam("rating") String rating) {
		List<Organizer> organizersList = organizerService.fetchByRating(rating);

		return new ResponseEntity<>(organizersList, HttpStatus.OK);
	}

	@GetMapping("/fetch-by-website")
	public ResponseEntity<?> fetchByWebsite(@RequestParam("website") String website) {
		Organizer organizer = null;
		try {
			organizer = organizerService.fetchByWebsite(website);
		} catch (OrganizerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(organizer, HttpStatus.OK);
	}

	@GetMapping("/delete-by-id")
	public ResponseEntity<?> deleteById(@RequestParam("id") Long organizerId) throws OrganizerNotFoundException {
		String message = organizerService.delete(organizerId);

		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@GetMapping("/fetch-by-isDeleted")
	public ResponseEntity<?> fetchByIsDeleted(@RequestParam("isDeleted") boolean isDeleted) {

		List<Organizer> organizerList = organizerService.fetchByIsDeleted(isDeleted);

		return new ResponseEntity<>(organizerList, HttpStatus.OK);
	}

	@GetMapping("/fetch-all")
	public ResponseEntity<?> fetchAll() {

		List<Organizer> organizerList = organizerService.fetchAll();

		return new ResponseEntity<>(organizerList, HttpStatus.OK);
	}

}

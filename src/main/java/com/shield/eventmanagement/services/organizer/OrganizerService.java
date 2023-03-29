package com.shield.eventmanagement.services.organizer;

import java.util.List;
import java.util.Optional;

import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.entities.Organizer;
import com.shield.eventmanagement.exceptions.InvalidException;
import com.shield.eventmanagement.exceptions.organizer.OrganizerNotFoundException;
import com.shield.eventmanagement.exceptions.user.UserNotFoundException;
import com.shield.eventmanagement.request.organizer.OrganizerRequest;
import com.shield.eventmanagement.request.organizer.OrganizerUpdateRequest;

public interface OrganizerService {

	Organizer create(OrganizerRequest organizerRequest) throws InvalidException, UserNotFoundException;
	
	Organizer update(OrganizerUpdateRequest organizerUpdateRequest) throws OrganizerNotFoundException, InvalidException;
	
	String delete(Long organizerId) throws OrganizerNotFoundException;
	
	Optional<Organizer> fetchById(Long organizerId) throws OrganizerNotFoundException;
	
    Organizer fetchByUsername(String username) throws OrganizerNotFoundException;
	
	List<Organizer> fetchAll();
	
	List<Event> fetchEventByOrganizerId(Long id);
}

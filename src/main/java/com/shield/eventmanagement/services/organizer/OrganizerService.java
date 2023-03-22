package com.shield.eventmanagement.services.organizer;

import com.shield.eventmanagement.entities.Organizer;

public interface OrganizerService {

	String create(Organizer organizer);
	
	String update(Organizer organizer);
	
	String delete(Long organizerId);
}

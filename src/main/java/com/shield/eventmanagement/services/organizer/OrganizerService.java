package com.shield.eventmanagement.services.organizer;

import java.util.List;

import com.shield.eventmanagement.entities.Organizer;
import com.shield.eventmanagement.exceptions.organizer.OrganizerNotFoundException;

public interface OrganizerService {

	String create(Organizer organizer);
	
	String update(Organizer organizer);
	
	String delete(Long organizerId) throws OrganizerNotFoundException;
	
	Organizer fetchById(Long organizerId) throws OrganizerNotFoundException;
	
    Organizer fetchByUsername(String username) throws OrganizerNotFoundException;
	
	Organizer fetchByWebsite(String website) throws OrganizerNotFoundException;
	
	List<Organizer> fetchByRating(String rating);

	List<Organizer> fetchByIsDeleted(boolean isDeleted);	
	
	List<Organizer> fetchAll();
}

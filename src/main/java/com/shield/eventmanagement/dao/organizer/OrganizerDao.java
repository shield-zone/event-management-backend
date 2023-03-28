package com.shield.eventmanagement.dao.organizer;

import java.util.List;
import java.util.Optional;

import com.shield.eventmanagement.entities.Organizer;

public interface OrganizerDao {

	Organizer save(Organizer organizer);
		
	String delete(Long organizerId);
	
	Optional<Organizer> fetchById(Long organizerId);
	
	Organizer fetchByUsername(String username);
	
	Organizer fetchByWebsite(String website);
	
	List<Organizer> fetchByRating(String rating);
	
	List<Organizer> fetchAll();
}

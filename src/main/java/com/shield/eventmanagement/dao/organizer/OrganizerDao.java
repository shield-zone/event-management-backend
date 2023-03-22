package com.shield.eventmanagement.dao.organizer;

import com.shield.eventmanagement.entities.Organizer;

public interface OrganizerDao {

	Organizer save(Organizer organizer);
		
	String delete(Long organizerId);
	
	Organizer fetchById(Long organizerId);
}

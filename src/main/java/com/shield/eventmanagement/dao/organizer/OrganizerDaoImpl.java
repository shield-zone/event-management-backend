package com.shield.eventmanagement.dao.organizer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shield.eventmanagement.entities.Organizer;
import com.shield.eventmanagement.repositories.organizer.OrganizerRepository;

@Repository
public class OrganizerDaoImpl implements OrganizerDao{

	
	@Autowired
	OrganizerRepository organizerRepository;
	
	@Override
	public Organizer save(Organizer organizer) {
		
		organizer = organizerRepository.save(organizer);
		return organizer;
	}

	@Override
	public String delete(Long organizerId) {
	
		organizerRepository.deleteById(organizerId);
		return "Successfull deleted";
	}

	@Override
	public Organizer fetchById(Long organizerId) {
		
	    Organizer organizer = organizerRepository.findById(organizerId).get();
	    
	    if(organizer==null)
	    {
	    	System.out.println("exception throw karna he");
	    	return null;
	    }
	    
		return organizer;
	}

	@Override
	public Organizer fetchByUsername(String username) {
		
		Organizer organizer = organizerRepository.findByEmailId(username);
		return organizer;
	}

	@Override
	public Organizer fetchByWebsite(String website) {
		
		Organizer organizer = organizerRepository.findByWebsite(website);
		return organizer;
	}

	@Override
	public List<Organizer> fetchByRating(String rating) {
		
		List<Organizer> organizerList = organizerRepository.findByRating(rating);
		return organizerList;
	}

	@Override
	public List<Organizer> fetchAll() {
		
		List<Organizer> organizerLists = organizerRepository.findAll(); 
		
	    return organizerLists;
	}

}

package com.shield.eventmanagement.dao.organizer;

import java.util.List;
import java.util.Optional;

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
	public Optional<Organizer> fetchById(Long organizerId) {

		return organizerRepository.findById(organizerId);
	}

	@Override
	public Organizer fetchByUsername(String username) {

		return organizerRepository.findByEmailId(username);
	}

	@Override
	public Organizer fetchByWebsite(String website) {

		return organizerRepository.findByWebsite(website);
	}

	@Override
	public List<Organizer> fetchByRating(String rating) {

		return organizerRepository.findByRating(rating);
	}

	@Override
	public List<Organizer> fetchAll() {

		return organizerRepository.findAll();
	}

}

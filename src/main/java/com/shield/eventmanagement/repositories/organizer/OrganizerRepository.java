package com.shield.eventmanagement.repositories.organizer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shield.eventmanagement.entities.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

	Organizer findByEmailId(String email);
	
	List<Organizer> findByRating(String rating);
	
	Organizer findByWebsite(String webiste);
	
	@Query("from Organizer where isDeleted = ?1")
	List<Organizer> findByIsDeleted(boolean isDeleted);
	
}

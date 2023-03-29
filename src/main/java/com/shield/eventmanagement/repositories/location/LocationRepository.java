package com.shield.eventmanagement.repositories.location;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shield.eventmanagement.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	
}

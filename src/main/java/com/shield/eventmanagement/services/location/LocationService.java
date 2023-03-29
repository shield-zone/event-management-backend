package com.shield.eventmanagement.services.location;

import java.util.List;
import java.util.Optional;

import com.shield.eventmanagement.entities.Location;
import com.shield.eventmanagement.exceptions.location.LocationNotFoundException;
import com.shield.eventmanagement.request.location.LocationUpdateRequest;

public interface LocationService {
	 
	Location create(Location location);
	
	Location update(LocationUpdateRequest locationUpdateRequest) throws LocationNotFoundException;
    
	Optional<Location> findByLocationId(Long locationId) throws LocationNotFoundException;
	
	List<Location> fetchAll();
}

package com.shield.eventmanagement.services.location;

import java.util.List;
import java.util.Optional;

import com.shield.eventmanagement.entities.Location;
import com.shield.eventmanagement.request.location.LocationUpdateRequest;

public interface LocationService {
	 
	Location create(Location location);
	
	Location update(LocationUpdateRequest locationUpdateRequest);
    
	Optional<Location> findByLocationId(Long locationId);
	
	List<Location> fetchAll();
}

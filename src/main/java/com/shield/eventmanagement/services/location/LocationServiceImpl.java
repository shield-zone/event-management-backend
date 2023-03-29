package com.shield.eventmanagement.services.location;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.entities.Location;
import com.shield.eventmanagement.exceptions.location.LocationNotFoundException;
import com.shield.eventmanagement.repositories.location.LocationRepository;
import com.shield.eventmanagement.request.location.LocationUpdateRequest;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationRepository repository;

	@Override
	public Location create(Location location) {
		location = repository.save(location);

		return location;
	}

	@Override
	public Location update(LocationUpdateRequest locationUpdateRequest) throws LocationNotFoundException {
		Optional<Location> locationOptional = findByLocationId(locationUpdateRequest.getLocationId());
		Location location = locationOptional.get();

		if (locationUpdateRequest.getAddress() != null && !locationUpdateRequest.getAddress().isEmpty()) {
			location.setAddress(locationUpdateRequest.getAddress());
		}

		if (locationUpdateRequest.getCountry() != null && !locationUpdateRequest.getCountry().isEmpty()) {
			location.setCountry(locationUpdateRequest.getCountry());
		}

		if (locationUpdateRequest.getLocationName() != null && !locationUpdateRequest.getLocationName().isEmpty()) {
			location.setLocationName(locationUpdateRequest.getLocationName());
		}

		if (locationUpdateRequest.getPincode() != null && !locationUpdateRequest.getPincode().isEmpty()) {
			location.setPincode(locationUpdateRequest.getPincode());
		}

		if (locationUpdateRequest.getState() != null && !locationUpdateRequest.getState().isEmpty()) {
			location.setState(locationUpdateRequest.getState());
		}

		location = repository.save(location);

		return location;
	}

	@Override
	public Optional<Location> findByLocationId(Long locationId) throws LocationNotFoundException {
		
		Optional<Location> locationOptional =  repository.findById(locationId);
		
		if(!locationOptional.isPresent())
		{
			throw new LocationNotFoundException("Location with requested Id not found");
		}
		return locationOptional;
	}

	@Override
	public List<Location> fetchAll() {
		return repository.findAll();
	}
}

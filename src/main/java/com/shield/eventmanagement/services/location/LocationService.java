package com.shield.eventmanagement.services.location;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.entities.Location;
import com.shield.eventmanagement.repositories.location.LocationRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class LocationService {

	@Autowired
	LocationRepository repository;
	
	public Optional<Location> findByLocationId(Long locationId) {
		return repository.findByLocationId(locationId);
	}
	
	public Optional<Location> getLocationByName(String locationName) {
		return repository.getLocationByNameContainingIgnoreCase(locationName);
	}
	
	public Optional<Location> getAddress(String address) {
		return repository.getAddressContainingIgnoreCase(address);
	}
	
	public Optional<Location> getPincode(String pincode) {
		return repository.getPincodeContainingIgnoreCase(pincode);
	}
	
	public Optional<Location> getState(String state) {
		return repository.getStateContainingIgnoreCase(state);
	}
	
	public Optional<Location> getCountry(String coutry) {
		return repository.getCountryContainingIgnoreCase(coutry);
	}
}

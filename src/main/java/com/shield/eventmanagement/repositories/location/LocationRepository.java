package com.shield.eventmanagement.repositories.location;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.shield.eventmanagement.entities.Location;

@Repository
public interface LocationRepository {
	Optional<Location> findByLocationId(Long locationId);
	Optional<Location> getLocationByNameContainingIgnoreCase(String locationName);
	Optional<Location> getAddressContainingIgnoreCase(String address);
	Optional<Location> getPincodeContainingIgnoreCase(String pincode);
	Optional<Location> getStateContainingIgnoreCase(String state);
	Optional<Location> getCountryContainingIgnoreCase(String country);
}

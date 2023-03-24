package com.shield.eventmanagement.repositories.location;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shield.eventmanagement.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	Optional<Location> findByLocationId(Long locationId);

	// TODO: FIX THIS
	Optional<Location> getLocationByLocationNameContainingIgnoreCase(String locationName);

	Optional<Location> getLocationsByAddressContainingIgnoreCase(String address);

	// TODO: FIX THIS
	Optional<Location> getLocationsByPincodeContainingIgnoreCase(String pincode);
	Optional<Location> getLocationsByStateContainingIgnoreCase(String state);
	Optional<Location> getLocationByCountry(String country);
}

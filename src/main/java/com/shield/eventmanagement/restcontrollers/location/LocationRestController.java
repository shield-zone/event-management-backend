package com.shield.eventmanagement.restcontrollers.location;

import java.util.Optional;

import com.shield.eventmanagement.request.location.LocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.entities.Location;
import com.shield.eventmanagement.services.location.LocationService;

@RestController
@RequestMapping("/api/v1/location")
public class LocationRestController {
	
	@Autowired
	LocationService service;
	
	@PostMapping("/create-location")
	public ResponseEntity<?> createLocation(@RequestBody LocationRequest locationReq)
	{
		Location location = Location.builder()
				.address(locationReq.getAddress())
				.country(locationReq.getCountry())
				.locationName(locationReq.getLocationName())
				.pincode(locationReq.getPincode())
				.state(locationReq.getState())
				.build();

		location = service.create(location);
		
		return new ResponseEntity<>(location, HttpStatus.OK);
	}
	
	@GetMapping("find-by-location-id/{locationId}")
	Optional<Location> findByLocationId(@PathVariable Long locationId) {
		return service.findByLocationId(locationId);
	}
	
	@GetMapping("find-by-location-name/{locationName}")
	Optional<Location> getLocationByName(@PathVariable String locationName) {
		return service.getLocationByName(locationName);
	}
	
	@GetMapping("find-by-address/{address}")
	Optional<Location> getAddress(@PathVariable String address) {
		return service.getAddress(address);
	}
	
	@GetMapping("find-by-pincode/{pincode}")
	Optional<Location> getPincode(@PathVariable String pincode) {
		return service.getPincode(pincode);
	}
	
	@GetMapping("find-by-state/{state}")
	Optional<Location> getState(@PathVariable String state) {
		return service.getState(state);
	}
	
	@GetMapping("find-by-country/{country}")
	Optional<Location> getCountry(String country) {
		return service.getCountry(country);
	}
}

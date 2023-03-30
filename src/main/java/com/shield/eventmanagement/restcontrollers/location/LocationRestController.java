package com.shield.eventmanagement.restcontrollers.location;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shield.eventmanagement.entities.Location;
import com.shield.eventmanagement.exceptions.location.LocationNotFoundException;
import com.shield.eventmanagement.request.location.LocationRequest;
import com.shield.eventmanagement.request.location.LocationUpdateRequest;
import com.shield.eventmanagement.services.location.LocationService;

@RestController
@RequestMapping("/api/v1/location")
public class LocationRestController {

	@Autowired
	LocationService service;

	@PostMapping("/create-location")
	public ResponseEntity<?> createLocation(@Valid@RequestBody LocationRequest locationReq) {
		Location location = Location.builder().address(locationReq.getAddress()).country(locationReq.getCountry())
				.locationName(locationReq.getLocationName()).pincode(locationReq.getPincode())
				.state(locationReq.getState()).build();

		location = service.create(location);

		return new ResponseEntity<>(location, HttpStatus.OK);
	}

	@PutMapping("/update-location")
	public ResponseEntity<?> updateLocation(@Valid@RequestBody LocationUpdateRequest locationUpdateRequest) throws LocationNotFoundException {
		Location location = service.update(locationUpdateRequest);

		return new ResponseEntity<>(location, HttpStatus.OK);
	}

	@GetMapping("find-by-location-id/{locationId}")
	public ResponseEntity<?> findByLocationId(@PathVariable Long locationId) throws LocationNotFoundException {

		Location location = service.findByLocationId(locationId).get();
		return new ResponseEntity<>(location, HttpStatus.OK);
	}

	@GetMapping("fetch-all")
	public ResponseEntity<?> fetchAll() {

		List<Location> locationList = service.fetchAll();

		return new ResponseEntity<>(locationList, HttpStatus.OK);
	}
}

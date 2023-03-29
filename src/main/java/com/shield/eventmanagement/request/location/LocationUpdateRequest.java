package com.shield.eventmanagement.request.location;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationUpdateRequest {

	@NotNull
    private Long locationId;
	
	private String locationName;
	
	private String address;
	
	private String pincode;
	
	private String state;
	
	private String country;
}

package com.shield.eventmanagement.request.organizer;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.entities.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerRequest {

	
	@NotNull
	private Long id;
		
	@Size(min=10,max=10, message="Contact number should be of 10 digits")
	private String phoneNumber;
	
	@NotEmpty(message="Please provide the year in which your org found")
	private String presentSince;
	
	@Size(min=1, max=5, message="Rating scale is from 1 to 5")
	private String rating;
	
	private String website;
	
	@NotNull(message="Should have some given events")
	private Long eventId;
}

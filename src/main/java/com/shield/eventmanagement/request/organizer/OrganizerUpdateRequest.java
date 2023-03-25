package com.shield.eventmanagement.request.organizer;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.shield.eventmanagement.entities.Event;

import lombok.Data;

@Data
public class OrganizerUpdateRequest {

	@NotNull
	private String email;
	
    private String organizerName;
	
	private String phoneNumber;
	
	private String presentSince;
	
	private String rating;
	
	private boolean isDeleted;
	
	private String website;
	
	private List<Event> events;
}

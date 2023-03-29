package com.shield.eventmanagement.request.organizer;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrganizerUpdateRequest {

	@NotNull
	private Long organizerId;

	private String phoneNumber;

	private String presentSince;

	private String rating;

	private String website;	
}

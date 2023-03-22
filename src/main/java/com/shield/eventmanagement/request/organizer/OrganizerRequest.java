package com.shield.eventmanagement.request.organizer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;

import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.entities.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerRequest {

    private String organizerName;
	
	private String phoneNumber;
	
	private String emailId;
	
	private String presentSince;
	
	private String rating;
	
	private String website;
	
	private List<Location> locations = new ArrayList<Location>();
	
	private List<Event> events = new ArrayList<Event>();
}

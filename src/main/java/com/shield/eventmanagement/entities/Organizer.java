package com.shield.eventmanagement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organizer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long organizerId;
	
	private String organizerName;
	
	private String phoneNumber;
	
	private String emailId;
	
	private String presentSince;
	
	private String rating;
	
	//optional
	private String website;
	
	private boolean isDeleted;
	
	@OneToMany
	private List<Location> locations = new ArrayList<Location>();
	
	@ManyToMany
	private List<Event> events = new ArrayList<Event>();
	
	public void setLocations(List<Location> locations)
	{
		this.locations = locations;
		
		for(Location location: locations)
		{
			//location.setOrganizer(this);
		}
	}
	
	public void setEvents(List<Event> events)
	{
		this.events = events;
		
		for(Event event: events)
		{
			//event.setOrganizer(this);
		}
	}
	
	
}

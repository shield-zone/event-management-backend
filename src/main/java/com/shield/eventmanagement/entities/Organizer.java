package com.shield.eventmanagement.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organizer {

	@Id
	@Column(name = "organizer_id")
	private Long organizerId;
	
	private String organizerName;
	
	private String phoneNumber;
	
	private String emailId;
	
	private String presentSince;
	
	private String rating;
	
	private boolean isDeleted;
	//optional
	private String website;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "organizer")
	@JsonBackReference
	private List<Event> events;
	
	public void setEvents(List<Event> events)
	{
	   this.events = events;
	   for(Event event: events)
	   {
		   event.setOrganizer(this);
	   }
	}
	
}

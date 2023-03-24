package com.shield.eventmanagement.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.UniqueElements;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long organizerId;
	
	private String organizerName;
	
	private String phoneNumber;
	
	@UniqueElements
	private String emailId;
	
	private String presentSince;
	
	private String rating;
	
	//optional
	private String website;
	
	private boolean isDeleted;
	
//	@OneToOne(cascade= CascadeType.ALL, mappedBy ="organizers")
//	private List<Location> locations;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy ="organizers")
	private List<Event> events;
	
	@ManyToMany(cascade= CascadeType.ALL, mappedBy ="organizers")
	private List<Attendee> attendees;	
}

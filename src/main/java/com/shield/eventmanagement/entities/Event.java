package com.shield.eventmanagement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long eventId;
	
	private String eventName;
	
	private String eventType;
	
	private String startDate;
	
	private String endDate;
	
	private Double eventPrice;
	
	@ManyToMany
	@JoinTable(
	        name = "event_organizer",
	        joinColumns = @JoinColumn(name = "event_id"),
	        inverseJoinColumns = @JoinColumn(name = "organizer_id")
	    )
	private List<Organizer> organizers = new ArrayList<Organizer>();
	
}


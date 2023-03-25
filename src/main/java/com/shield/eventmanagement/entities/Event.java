package com.shield.eventmanagement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_id")
	private Long eventId;
	
	private String eventName;
	
	private String eventType;
	
	private String startDate;
	
	private String endDate;
	
	private Double eventPrice;
	
	@ManyToMany(mappedBy = "event")
	private List<Attendee> attendees = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	@JsonManagedReference
	private Location location;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "organizer_id", referencedColumnName = "organizer_id")
	@JsonBackReference
	private Organizer organizer;
}


package com.shield.eventmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "location_id")
	private Long locationId;
	
	private String locationName;
	
	private String address;
	
	private String pincode;
	
	private String state;
	
	private String country;

	
	@ManyToOne
	private Organizer organizer = new Organizer();
	

	@OneToOne(mappedBy = "location")
	@JsonBackReference
	private Event event;
}

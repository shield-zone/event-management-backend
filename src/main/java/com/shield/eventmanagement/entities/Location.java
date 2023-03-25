package com.shield.eventmanagement.entities;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
=======
import javax.persistence.*;
>>>>>>> dev

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
<<<<<<< HEAD
	
	@ManyToOne
	private Organizer organizer = new Organizer();
	
=======
>>>>>>> dev

	@OneToOne()
	private Event event;
}

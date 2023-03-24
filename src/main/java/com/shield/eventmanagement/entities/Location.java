package com.shield.eventmanagement.entities;

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

	@OneToOne()
	private Event event;
}

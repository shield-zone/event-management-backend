package com.shield.eventmanagement.entities;

import java.util.List;

import javax.persistence.*;

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
	@Column(name = "organizer_id")
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
	
	@OneToMany(mappedBy = "organizer")
	private List<Event> events = new ArrayList<>();
}

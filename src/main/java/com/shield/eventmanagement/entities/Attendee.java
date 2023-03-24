package com.shield.eventmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {

    @Id
    @GeneratedValue
    long attendeeId;

    @Transient
    String organizerName;

    Long organizerId;

    @Column(length = 50)
    String eventName;

    @Column(length = 50)
    String location;

    @Column(length = 50)
    String name;

    @Column(length = 50)
    String email;

//    @ManyToMany
//	@JoinTable(
//	        name = "attendee_organizer",
//	        joinColumns = @JoinColumn(name = "attendee_id"),
//	        inverseJoinColumns = @JoinColumn(name = "organizer_id")
//	    )
//	private List<Organizer> organizers = new ArrayList<Organizer>();
    
    int numberOfMember;

    boolean cancelledRegistration;
}

package com.shield.eventmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendee {

    @Id
    @GeneratedValue
    long attendeeId;

    @Transient
    String organizerName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizer_id")
    Organizer organizer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    Event event;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    Location location;

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

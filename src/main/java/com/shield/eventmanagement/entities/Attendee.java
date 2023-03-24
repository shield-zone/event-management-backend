package com.shield.eventmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    List<Event> event = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    List<Location> location;

    @Column(length = 50)
    String name;

    @Column(length = 50)
    String email;

    int numberOfMember;

    boolean cancelledRegistration;
}

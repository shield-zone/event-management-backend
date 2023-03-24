package com.shield.eventmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attendee_id")
    long attendeeId;

    @Transient
    String organizerName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private List<Event> event = new ArrayList<>();

    @Column(length = 50)
    String name;

    @Column(length = 50)
    String email;

    int numberOfMember;

    boolean cancelledRegistration;
}

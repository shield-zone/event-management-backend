package com.shield.eventmanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attendee_id")
    long attendeeId;

    private Long user_id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    @JsonManagedReference
    private List<Event> event = new ArrayList<>();

    @Column(length = 50)
    String name;

    @Column(length = 50)
    String email;

    int numberOfMember;

    boolean cancelledRegistration;
}

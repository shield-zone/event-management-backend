package com.shield.eventmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
    @JsonBackReference
    private List<Event> event = new ArrayList<>();

    boolean cancelledRegistration;
}

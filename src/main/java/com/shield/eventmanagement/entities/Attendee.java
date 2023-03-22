package com.shield.eventmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    int numberOfMember;

    boolean cancelledRegistration;
}

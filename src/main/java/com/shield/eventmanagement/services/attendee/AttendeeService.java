package com.shield.eventmanagement.services.attendee;

import com.shield.eventmanagement.dao.attendee.AttendeeDao;
import com.shield.eventmanagement.entities.Attendee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeService implements AttendeeServiceInterface {

    @Autowired
    AttendeeDao dao;

    public Optional<Attendee> insertAttendee(Attendee attendee) {
        return dao.insertAttendee(attendee);
    }

    public Optional<Attendee> updateAttendee(Attendee attendee) {
        return insertAttendee(attendee);
    }
}

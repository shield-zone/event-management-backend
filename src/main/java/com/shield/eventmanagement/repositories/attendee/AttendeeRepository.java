package com.shield.eventmanagement.repositories.attendee;

import com.shield.eventmanagement.entities.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    List<Attendee> getEventByAttendeeId(Long id);
}

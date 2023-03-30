package com.shield.eventmanagement.restcontrollers.attendee;

import com.shield.eventmanagement.dao.user.UserDao;
import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.exceptions.event.EventNotFoundException;
import com.shield.eventmanagement.request.attendee.AttendeeRequest;
import com.shield.eventmanagement.request.attendee.AttendeeUpdateRequest;
import com.shield.eventmanagement.services.attendee.AttendeeService;
import com.shield.eventmanagement.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/attendee")
public class AttendeeRestController {

    @Autowired
    AttendeeService service;
    @Autowired
    EventService eventService;

    @Autowired
    UserDao userDao;

    @PostMapping
    public Optional<Attendee> insertAttendee(@Valid @RequestBody AttendeeRequest attendeeReq) throws EventNotFoundException {
        Optional<Event> event = eventService.findByEventId(attendeeReq.getEventId());
        Optional<User> user = userDao.findById(attendeeReq.getUser_id());
        if (!event.isPresent() || !user.isPresent()) return Optional.empty();

        Attendee attendee = Attendee
                .builder()
                .event(Collections.singletonList(event.get()))
                .user_id(attendeeReq.getUser_id())
                .cancelledRegistration(attendeeReq.isCancelledRegistration())
                .build();

        return service.insertAttendee(attendee);
    }

    @PutMapping
    public ResponseEntity<Attendee> updateAttendee(@RequestBody AttendeeUpdateRequest attendeeReq) throws EventNotFoundException {
        Optional<Event> event = eventService.findByEventId(attendeeReq.getEventId());
        Optional<User> user = userDao.findById(attendeeReq.getUser_id());
        if (!event.isPresent() || !user.isPresent()) return ResponseEntity.status(417).body(null);

        Optional<Attendee> optionalAttendee = service.updateAttendee(
                Attendee.builder()
                        .attendeeId(attendeeReq.getAttendeeId())
                        .event(Collections.singletonList(event.get()))
                        .user_id(user.get().getUserId())
                        .cancelledRegistration(true)
                        .build()
        );
        return optionalAttendee.map(
                value -> ResponseEntity.status(200).body(value)
        ).orElseGet(
                () -> ResponseEntity.status(417).body(null)
        );
    }
}

package com.shield.eventmanagement.restcontrollers.attendee;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.request.attendee.AttendeeRequest;
import com.shield.eventmanagement.services.attendee.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/attendee")
public class AttendeeRestController {

    @Autowired
    AttendeeService service;

    @GetMapping("find-by-email/{email}")
    public List<Attendee> findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @GetMapping("find-by-name/{name}")
    public List<Attendee> getAttendeesByName(@PathVariable String name) {
        return service.getAttendeesByName(name);
    }

    @GetMapping("get-attendees-by-location/{location}")
    public List<Attendee> getAttendeesByLocation(@PathVariable String location) {
        return service.getAttendeesByLocation(location);
    }

    @GetMapping("get-event-attendees/{eventName}")
    public List<Attendee> getAttendeesByEventName(@PathVariable String eventName) {
        return service.getAttendeesByEventName(eventName);
    }

    @PutMapping("cancel-event-registration")
    public Optional<Attendee> cancelRegistrationByEventName(@RequestBody Attendee attendee) {
        return service.cancelRegistrationByEventName(attendee);
    }

    @GetMapping("get-cancelled-attendees-by-event/{eventName}")
    public List<Attendee> getCancelledAttendeesByEventName(@PathVariable String eventName) {
        return service.getCancelledAttendeesByEventName(eventName);
    }

    @PostMapping
    public Attendee insertAttendee(@RequestBody AttendeeRequest attendeeReq) {
        Event event = Event.builder()
                .eventId(attendeeReq.getEventId())
                .build();

        Attendee attendee = Attendee
                .builder()
                .event(Collections.singletonList(event))
                .name(attendeeReq.getName())
                .email(attendeeReq.getEmail())
                .numberOfMember(attendeeReq.getNumberOfMember())
                .cancelledRegistration(attendeeReq.isCancelledRegistration())
                .build();

        return service.insertAttendee(attendee);
    }

    @PutMapping
    public Optional<Attendee> updateAttendee(@RequestBody Attendee attendee) {
        return service.updateAttendee(attendee);
    }
}

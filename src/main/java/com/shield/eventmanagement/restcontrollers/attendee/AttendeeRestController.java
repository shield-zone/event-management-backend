package com.shield.eventmanagement.restcontrollers.attendee;

import com.shield.eventmanagement.entities.Attendee;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.request.attendee.AttendeeRequest;
import com.shield.eventmanagement.services.attendee.AttendeeService;
import com.shield.eventmanagement.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@RequestMapping("/api/v1/attendee")
public class AttendeeRestController {

    @Autowired
    AttendeeService service;
    @Autowired
    EventService eventService;

    @GetMapping("find-by-email/{email}")
    public List<Attendee> findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @GetMapping("find-by-name/{name}")
    public List<Attendee> getAttendeesByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PostMapping
    public Attendee insertAttendee(@Valid @RequestBody AttendeeRequest attendeeReq) {
        Optional<Event> event = eventService.findByEventId(attendeeReq.getEventId());
        if (!event.isPresent()) return null;

        System.out.println("----------------> " + event.get().getEventId());

        Attendee attendee = Attendee
                .builder()
                .event(new ArrayList<>(Arrays.asList(event.get())))
                .user_id(attendeeReq.getUser_id())
                .name(attendeeReq.getName())
                .email(attendeeReq.getEmail())
                .numberOfMember(attendeeReq.getNumberOfMember())
                .cancelledRegistration(attendeeReq.isCancelledRegistration())
                .build();

        return service.insertAttendee(attendee);
    }

    @PutMapping
    public ResponseEntity<Attendee> updateAttendee(@RequestBody Attendee attendee) {
        Optional<Attendee> optionalAttendee = service.updateAttendee(attendee);
        return optionalAttendee.map(
                value -> ResponseEntity.status(200).body(value)
        ).orElseGet(
                () -> ResponseEntity.status(404).body(null)
        );
    }
}

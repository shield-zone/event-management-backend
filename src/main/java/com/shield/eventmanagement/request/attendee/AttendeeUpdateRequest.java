package com.shield.eventmanagement.request.attendee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeUpdateRequest {
    @NotNull
    private long attendeeId;

    @NotNull
    private long eventId;

    @NotNull
    private long userId;

    private boolean cancelRegistration;
}

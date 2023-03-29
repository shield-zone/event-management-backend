package com.shield.eventmanagement.request.attendee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendeeRequest {
    @NotNull
    private Long eventId;

    @NotNull
    private Long user_id;

    @NotNull
    boolean cancelledRegistration;
}

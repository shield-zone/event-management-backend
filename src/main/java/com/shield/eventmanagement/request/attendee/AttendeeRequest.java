package com.shield.eventmanagement.request.attendee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendeeRequest {
    @NotEmpty(message = "Required organized id")
    private Long organizerId;
    @NotEmpty(message = "Required event id")
    private Long eventId;
    @NotEmpty(message = "Required location id")
    private Long locationId;
    @NotEmpty
    @Size(min=10, max=50, message = "Required email id")
    private String email;
    @NotEmpty
    @Size(min=10, max=50, message = "Required name")
    private String name;
    @NotEmpty
    int numberOfMember;
    @NotEmpty
    boolean cancelledRegistration;
}

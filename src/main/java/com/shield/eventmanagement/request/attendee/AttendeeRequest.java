package com.shield.eventmanagement.request.attendee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendeeRequest {
    @NotNull
    private Long eventId;

    @NotNull
    private Long user_id;

    @NotEmpty
    @Size(min=10, max=50, message = "Required email id")
    private String email;

    @NotEmpty
    @Size(min=10, max=50, message = "Required name")
    private String name;

    @NotNull
    int numberOfMember;

    @NotNull
    boolean cancelledRegistration;
}

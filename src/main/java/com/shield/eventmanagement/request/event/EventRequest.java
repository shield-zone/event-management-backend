package com.shield.eventmanagement.request.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NotNull
    private Long eventId;

    @NotEmpty
    private String endDate;

    @NotEmpty
    private String startDate;

    @NotEmpty
    private String eventName;
    @NotNull
    private double eventPrice;

    @NotNull
    private Long locationId;
    @NotNull
    private Long organizerId;
}

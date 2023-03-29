package com.shield.eventmanagement.request.event;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NotEmpty
    private String endDate;

    @NotEmpty
    private String startDate;

    @NotEmpty
    private String eventName;
    @NotNull
    private Double eventPrice;

    @NotNull
    private Long locationId;
    @NotNull
    private Long organizerId;
}

package com.shield.eventmanagement.request.event;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventUpdateRequest {

	@NotNull
    private Long eventId;

    private String endDate;

    private String startDate;

    private String eventName;
    
    private Double eventPrice;
}

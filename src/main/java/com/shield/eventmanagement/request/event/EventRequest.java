package com.shield.eventmanagement.request.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NotNull
    private Long eventId;

}

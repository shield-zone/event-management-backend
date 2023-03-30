package com.shield.eventmanagement.request.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventOrganizerLocationRequest {

    @NotEmpty
    private String endDate;
    @NotEmpty
    private String startDate;
    @NotEmpty
    private String eventName;
    @NotNull
    private double eventPrice;

    @NotEmpty
    private String address;
    @NotEmpty
    private String country;
    @NotEmpty
    private String locationName;
    @NotEmpty
    private String pincode;
    @NotEmpty
    private String state;

    @NotNull
    private Long userId;
    
    @Size(min=10,max=10, message="Contact number should be of 10 digits")
    private String phoneNumber;

    @NotEmpty(message="Please provide the year in which your org found")
    private String presentSince;

    @Size(min=1, max=5, message="Rating scale is from 1 to 5")
    private String rating;

    private String website;

}

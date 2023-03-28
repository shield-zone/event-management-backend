package com.shield.eventmanagement.request.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
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
}

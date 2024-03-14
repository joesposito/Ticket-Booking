package com.trainer.ticketbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StationRequestDto {
    private String name;
    private String addressLine1;
    private String city;
    private String country;
    private String postalCode;

    @Override
    public String toString() {
        return "StationRegistrationBody{" +
                "name='" + name + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}

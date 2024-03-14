package com.trainer.ticketbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocalUserRequestDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String postalCode;

    @Override
    public String toString() {
        return "LocalUserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}

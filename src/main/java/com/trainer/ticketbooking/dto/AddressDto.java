package com.trainer.ticketbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String postalCode;

    //pull down the address and make it into an address dto, update using the localuser dto then save the address object

    @Override
    public String toString() {
        return "AddressDto{" +
                "addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}

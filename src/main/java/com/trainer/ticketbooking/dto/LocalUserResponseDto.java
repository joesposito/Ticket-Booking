package com.trainer.ticketbooking.dto;

import com.trainer.ticketbooking.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalUserResponseDto {
    private Long userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Address address;

    @Override
    public String toString() {
        return "LocalUserResponseDto{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                '}';
    }
}

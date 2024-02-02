package com.trainer.ticketbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainDto {
    private long departureStationID;
    private long arrivalStationID;
    private long trainID;

    @Override
    public String toString() {
        return "TrainRegistrationBody{" +
                "departureStationID=" + departureStationID +
                ", arrivalStationID=" + arrivalStationID +
                '}';
    }
}

package com.trainer.ticketbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainRequestDto {
    private long departureStationID;
    private long arrivalStationID;

    @Override
    public String toString() {
        return "TrainDto{" +
                "departureStationID=" + departureStationID +
                ", arrivalStationID=" + arrivalStationID +
                '}';
    }
}

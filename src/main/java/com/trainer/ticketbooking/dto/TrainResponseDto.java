package com.trainer.ticketbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrainResponseDto {
    private long trainID;
    private long departureStationID;
    private long arrivalStationID;

    @Override
    public String toString() {
        return "TrainResponseDto{" +
                "trainID=" + trainID +
                ", departureStationID=" + departureStationID +
                ", arrivalStationID=" + arrivalStationID +
                '}';
    }
}

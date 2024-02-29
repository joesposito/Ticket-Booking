package com.trainer.ticketbooking.dto;

import com.trainer.ticketbooking.entity.Station;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainDto {
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

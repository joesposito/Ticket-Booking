package com.trainer.ticketbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDto {
    long trainID;
    String seatClass;
    double cost;
    int seatNumber;

    @Override
    public String toString() {
        return "TicketRequestDto{" +
                "trainID=" + trainID +
                ", seatClass='" + seatClass + '\'' +
                ", cost=" + cost +
                ", seatNumber=" + seatNumber +
                '}';
    }
}

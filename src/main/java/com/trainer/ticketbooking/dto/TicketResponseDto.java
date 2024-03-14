package com.trainer.ticketbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponseDto {
    long ticketID;
    long trainID;
    String seatClass;
    double cost;
    int seatNumber;

    @Override
    public String toString() {
        return "TicketResponseDto{" +
                "ticketID=" + ticketID +
                ", trainID=" + trainID +
                ", seatClass='" + seatClass + '\'' +
                ", cost=" + cost +
                ", seatNumber=" + seatNumber +
                '}';
    }
}

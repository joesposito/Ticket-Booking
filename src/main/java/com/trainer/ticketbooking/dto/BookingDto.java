package com.trainer.ticketbooking.dto;

public class BookingDto {
    double price;
    long userID;
    long trainID;
    String seatClass;
    int quantity;

    @Override
    public String toString() {
        return "BookingDto{" +
                "price=" + price +
                ", userID=" + userID +
                ", trainID=" + trainID +
                ", seatClass='" + seatClass + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

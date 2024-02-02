package com.trainer.ticketbooking.entity;

public enum SeatClass {
    FIRST("First"), SECOND("Second");
    private String classification;

    SeatClass(String classification) {
        this.classification = classification;
    }
}

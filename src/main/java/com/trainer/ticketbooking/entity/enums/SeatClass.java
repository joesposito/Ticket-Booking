package com.trainer.ticketbooking.entity.enums;

public enum SeatClass {
    FIRST("First"), SECOND("Second");
    private final String classification;

    SeatClass(String classification) {
        this.classification = classification;
    }
}

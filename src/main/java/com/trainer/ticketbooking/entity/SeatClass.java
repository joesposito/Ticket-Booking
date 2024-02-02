package com.trainer.ticketbooking.entity;

public enum SeatClass {
    FIRST("First"), SECOND("Second");
    private final String classification;

    SeatClass(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }
}

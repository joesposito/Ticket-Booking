package com.trainer.ticketbooking;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestEntity {

    @Id
    public long id;
    @Column
    public String username;
}

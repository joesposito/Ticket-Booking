package com.trainer.ticketbooking.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Long bookingID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "local_user_user_id", nullable = false)
    private LocalUser localUser;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "booking_booking_id", unique = true)
    private Set<Ticket> tickets = new LinkedHashSet<>();
}
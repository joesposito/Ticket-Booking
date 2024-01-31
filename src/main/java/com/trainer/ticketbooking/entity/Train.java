package com.trainer.ticketbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "departure_station_id", nullable = false)
    private Station departureStation;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "arrival_station_id", nullable = false)
    private Station arrivalStation;
}
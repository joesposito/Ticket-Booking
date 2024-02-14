package com.trainer.ticketbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id", nullable = false)
    private Long trainID;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "departure_station_id", nullable = false)
    private Station departureStation;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "arrival_station_id", nullable = false)
    private Station arrivalStation;

    @OneToMany(mappedBy = "train", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public String toString() {
        return "Train{" +
                "trainID=" + trainID +
                ", departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ", tickets=" + tickets +
                '}';
    }
}
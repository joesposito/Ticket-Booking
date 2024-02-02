package com.trainer.ticketbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id", nullable = false)
    private Long stationID;

    @Column(name = "name", nullable = false, length = 512)
    private String name;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Override
    public String toString() {
        return "Station{" +
                "stationID=" + stationID +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
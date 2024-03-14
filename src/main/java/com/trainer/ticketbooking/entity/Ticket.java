package com.trainer.ticketbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.trainer.ticketbooking.entity.enums.SeatClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private Long ticketID;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_class", nullable = false, length = 50)
    private SeatClass seatClass;

    @Column(name = "seat_number", nullable = false, unique = true)
    private Integer seatNumber;

}
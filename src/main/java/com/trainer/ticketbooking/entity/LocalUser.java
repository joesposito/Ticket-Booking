package com.trainer.ticketbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "local_user")
public class LocalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userID;

    @Column(name = "username", nullable = false, unique = true, length = 320)
    private String username;

    @Column(name = "password", nullable = false, length = 120)
    private String password;

    @Column(name = "first_name", nullable = false, length = 35)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 35)
    private String lastName;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
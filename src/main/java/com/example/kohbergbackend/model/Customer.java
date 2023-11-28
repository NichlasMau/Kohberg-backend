package com.example.kohbergbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int costumerID;
    private String name;
    //TODO Password skal kunne ændres af kunde og salesperson, måske en mail med engangskode
    private String password;
    private String role;
    private LocalDate birthday;
    private String email;
    private LocalDate creationYear;

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private Leader leader;

    @ManyToOne
    @JoinColumn(name = "salesperson_id")
    private Salesperson salesperson;


}

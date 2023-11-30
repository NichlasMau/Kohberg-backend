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
    private int customerID;
    private String name;
    //TODO Password skal kunne ændres af kunde og salesperson, måske en mail med engangskode
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

    public Customer(int customerID, String name, String role, LocalDate birthday, String email, LocalDate creationYear) {
        this.customerID = customerID;
        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.email = email;
        this.creationYear = creationYear;
    }

}

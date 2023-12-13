package com.example.kohbergbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Leader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaderID;

    private LocalDate hireDate;
    private String username;
    private String name;
    private String password;
    private String role;
    private LocalDate birthday;
    private String email;
    private LocalDate creationYear;

    @OneToMany(mappedBy = "leader")
    private List<Customer> customers;

    @OneToMany(mappedBy = "leader")
    private List<Salesperson> salespersons;

    public Leader(int leaderID, LocalDate hireDate, String username, List<Customer> customers, List<Salesperson> salespersons) {
        this.leaderID = leaderID;
        this.hireDate = hireDate;
        this.username = username;
        this.customers = customers;
        this.salespersons = salespersons;
    }

}



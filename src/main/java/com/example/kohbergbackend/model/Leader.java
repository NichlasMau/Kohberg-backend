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
public class Leader extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaderID;
    private LocalDate hireDate;
    private String username;

    @OneToMany(mappedBy = "leader")
    private List<Customer> customers;

    @OneToMany(mappedBy = "leader")
    private List<Salesperson> salespersons;



    public Leader(int leaderID, String name, String username, String role, LocalDate birthday, String email,
                  LocalDate hireDate, List<Customer> customers,
                  List<Salesperson> salespersons) {
        super(leaderID, name, role, birthday, email);
        this.username = username;
        this.hireDate = hireDate;
        this.customers = customers;
        this.salespersons = salespersons;
    }
}



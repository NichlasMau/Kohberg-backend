package com.example.kohbergbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Salesperson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salespersonID;

    private String username;
    private String name;
    private String password;
    private String role;
    private LocalDate birthday;
    private String email;
    private LocalDate creationYear;

    @OneToMany(mappedBy = "salesperson")
    private List<Customer> customers;

    @ManyToOne
    @JoinColumn(name = "leader_id") // sørg for, at dette navn matcher det, du har i Leader-klassen
    private Leader leader;

    public Salesperson(int salespersonID, String username, List<Customer> customers) {
        this.salespersonID = salespersonID;
        this.username = username;
        this.customers = customers;
    }

    public Salesperson(int id, String name, LocalDate birthday, List<Customer> customers, String email) {
        this.salespersonID = id;
        this.name = name;
        this.birthday = birthday;
        this.customers = customers;
        this.email = email;
    }

    @Transient
    public String getFormattedReminderDate() {
        // Vælg det ønskede format (fx. "dd.MM.yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("da", "DK"));
        // Formatér LocalDate til en streng
        return getBirthday().format(formatter);
    }
}

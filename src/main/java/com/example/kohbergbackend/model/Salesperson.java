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
public class Salesperson extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salespersonID;
    private String username;

    @OneToMany(mappedBy = "salesperson")
    private List<Customer> customers;

    public Salesperson(int salespersonID, String name, String username, String role, LocalDate birthday, List<Customer> customers, String email) {
        super(salespersonID, name, role, birthday, email);
        this.customers = customers;
        this.username = username;
    }

    @Transient
    public String getFormattedReminderDate() {
        // Vælg det ønskede format (fx. "dd.MM.yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("da", "DK"));
        // Formatér LocalDate til en streng
        return getBirthday().format(formatter);
    }
}

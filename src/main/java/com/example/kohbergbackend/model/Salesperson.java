package com.example.kohbergbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Salesperson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate birthday;
    private int customers;
    private String email;
    private String password;


    public Salesperson(int id, String name, LocalDate birthday, int customers, String email) {
        this.id = id;
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
        return birthday.format(formatter);
    }

}

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
public class Salesperson extends User{
    private int customer;

    public Salesperson(int id, String name, String role, LocalDate birthday, int customer, String email) {
        super(id, name, "salesperson", role, birthday, email);
        this.customer = customer;
    }



    @Transient
    public String getFormattedReminderDate() {
        // Vælg det ønskede format (fx. "dd.MM.yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("da", "DK"));
        // Formatér LocalDate til en streng
        return getBirthday().format(formatter);
    }
}

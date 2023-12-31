package com.example.kohbergbackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String name;
    //TODO Password skal kunne ændres af kunde og salesperson, måske en mail med engangskode
    private String password;
    private String role;
    private LocalDate birthday;
    private String email;
    private LocalDate creationYear;

    public User(int userID, String name, String role, LocalDate birthday, String email, LocalDate creationYear) {
        this.userID = userID;
        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.email = email;
        this.creationYear = creationYear;
    }



    @Transient
    public String getFormattedReminderDate() {
        // Vælg det ønskede format (fx. "dd.MM.yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("da", "DK"));
        // Formatér LocalDate til en streng
        return birthday.format(formatter);
    }
}

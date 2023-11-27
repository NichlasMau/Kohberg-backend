package com.example.kohbergbackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
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
    private String username;
    //TODO Password skal kunne ændres af kunde og salesperson, måske en mail med engangskode
    private String password;
    private String role;
    private LocalDate birthday;
    private String email;

    public User(int userID, String username, String role, LocalDate birthday, String email) {
        this.userID = userID;
        this.username = username;
        this.role = role;
        this.birthday = birthday;
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

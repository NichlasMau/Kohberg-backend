package com.example.kohbergbackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

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
}

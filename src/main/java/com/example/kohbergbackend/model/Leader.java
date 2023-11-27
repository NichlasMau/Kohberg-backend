package com.example.kohbergbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Leader extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaderID;
    private String position;
    private LocalDate hireDate;

    public Leader(int id, String name, String role, LocalDate birthday, String email, String position, LocalDate hireDate) {
        super(id, name, role, birthday, email);
        this.position = position;
        this.hireDate = hireDate;
    }
}

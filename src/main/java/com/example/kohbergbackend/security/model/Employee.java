package com.example.kohbergbackend.security.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate hireDate;
    private String username;
    private String password;
    private String role;
    private LocalDate creationYear;

    @Column(name = "local_time", columnDefinition = "TIME")
    private LocalTime localTime = LocalTime.of(6,43,12);

    public Employee(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}

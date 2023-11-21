package com.example.kohbergbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int costumerID;
    private String name;
    private LocalDate birthday;
    private LocalDate creationYear;



}

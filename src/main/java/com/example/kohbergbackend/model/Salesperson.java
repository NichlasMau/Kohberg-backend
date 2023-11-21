package com.example.kohbergbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.annotation.processing.Generated;
import java.util.Date;

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
    private Date birthday;
    private int customers;
    private String email;
    private String password;


    public Salesperson(int id, String name, Date birthday, int customers, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.customers = customers;
        this.email = email;
    }
}

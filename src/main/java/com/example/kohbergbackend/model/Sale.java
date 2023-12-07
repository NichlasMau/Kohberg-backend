package com.example.kohbergbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int saleID;
    private int customerID;
    private LocalDate saleDate;
    private String product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Sale(int saleID, int customerID, LocalDate saleDate, String product) {
        this.saleID = saleID;
        this.customerID = customerID;
        this.saleDate = saleDate;
        this.product = product;
        this.customer = customer;
    }

    @Transient
    public String getFormattedReminderDate() {
        // Vælg det ønskede format (fx. "dd.MM.yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("da", "DK"));
        // Formatér LocalDate til en streng
        return saleDate.format(formatter);
    }



}

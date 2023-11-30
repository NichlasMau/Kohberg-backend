package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.Salesperson;

import java.time.LocalDate;
import java.util.List;

public record LeaderDTO (
    int leaderID,
    LocalDate hireDate,
    String username,
    List<Customer> customers,
    java.util.List<Salesperson> salespersons
) { }
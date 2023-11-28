package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Customer;

import java.time.LocalDate;
import java.util.List;

public record SalespersonDTO (int id, String name, LocalDate birthday, List<Customer> customers, String email) {
}

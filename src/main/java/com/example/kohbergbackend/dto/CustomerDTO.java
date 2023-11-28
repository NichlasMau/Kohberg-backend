package com.example.kohbergbackend.dto;

import java.time.LocalDate;

public record CustomerDTO(int customerID,
                          String name,
                          String role,
                          LocalDate birthday,
                          String email,
                          LocalDate creationYear) {
}

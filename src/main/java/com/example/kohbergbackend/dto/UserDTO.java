package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.Salesperson;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public record UserDTO(
        int userID,
        String name,
        String username, // Felt til LeaderDTO
        String role,
        LocalDate birthday,
        String email,
        LocalDate creationYear,
        LocalDate hireDate,  // Felt specifikt for LeaderDTO
        List<Customer> customers,  // Liste specifik for LeaderDTO og SalespersonDTO
        List<Salesperson> salespersons  // Liste specifik for LeaderDTO
) {

}
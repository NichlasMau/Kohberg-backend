package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public record SalespersonDTO(int userID,
                             String name,
                             String username,
                             String role,
                             LocalDate birthday,
                             String email,
                             LocalDate creationYear,
                             List<Customer> customers
) {


}

package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.Salesperson;

import java.time.LocalDate;
import java.util.List;

public record ReminderDTO (
        int reminderID,
        int customerID,
        LocalDate reminderDate,
        String message

) { }
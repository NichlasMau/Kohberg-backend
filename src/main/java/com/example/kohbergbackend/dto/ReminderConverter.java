package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.Leader;
import com.example.kohbergbackend.model.Reminder;
import com.example.kohbergbackend.model.Salesperson;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReminderConverter {

    public Reminder toEntity(ReminderDTO reminderDTO) {
        return new Reminder(
                reminderDTO.reminderID(),
                reminderDTO.customerID(),
                reminderDTO.reminderDate(),
                reminderDTO.message()

        );
    }

    public ReminderDTO toDTO(Reminder reminder) {
        return new ReminderDTO(
                reminder.getReminderID(),
                reminder.getCustomerID(),
                reminder.getReminderDate(),
                reminder.getMessage()
        );
    }
}

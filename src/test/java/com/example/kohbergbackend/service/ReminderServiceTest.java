package com.example.kohbergbackend.service;

import com.example.kohbergbackend.exception.NotFoundException;
import com.example.kohbergbackend.model.Reminder;
import com.example.kohbergbackend.repository.ReminderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReminderServiceTest {

    @Autowired
    private ReminderService reminderService;

    @MockBean
    private ReminderRepository reminderRepository;

    @Test
    void createReminder() {
        // Arrange
        Reminder newReminder = new Reminder();
        when(reminderRepository.save(any())).thenReturn(newReminder);

        // Act
        Reminder createdReminder = reminderService.createReminder(newReminder);

        // Assert
        assertEquals(newReminder.getReminderID(), createdReminder.getReminderDate());
        // Additional assertions based on your model
    }

    @Test
    void getAllReminders() {
        // Arrange
        Reminder reminder1 = new Reminder();
        Reminder reminder2 = new Reminder();
        when(reminderRepository.findAll()).thenReturn(List.of(reminder1, reminder2));

        // Act
        List<Reminder> allReminders = reminderService.getAllReminders();

        // Assert
        assertEquals(2, allReminders.size());
        // Additional assertions based on your model
    }

    @Test
    void getReminderById_ExistingId_ReturnsReminder() {
        // Arrange
        Reminder existingReminder = new Reminder();
        when(reminderRepository.findById(existingReminder.getReminderID())).thenReturn(Optional.of(existingReminder));

        // Act
        Optional<Reminder> retrievedReminderOptional = reminderService.getReminderById(existingReminder.getReminderID());

        // Assert
        assertTrue(retrievedReminderOptional.isPresent());
        Reminder retrievedReminder = retrievedReminderOptional.get();
        assertEquals(existingReminder.getReminderID(), retrievedReminder.getReminderDate());
        // Additional assertions based on your model
    }

    @Test
    void getReminderById_NonExistingId_ThrowsNotFoundException() {
        // Arrange
        int nonExistingId = 999;
        when(reminderRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> reminderService.getReminderById(nonExistingId));
    }



}
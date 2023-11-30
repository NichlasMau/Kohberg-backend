package com.example.kohbergbackend.service;

import com.example.kohbergbackend.exception.NotFoundException;
import com.example.kohbergbackend.model.Reminder;
import com.example.kohbergbackend.repository.ReminderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReminderServiceTest {

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private ReminderRepository reminderRepository;

    @Test
    void createReminder() {
        // Arrange
        Reminder newReminder = new Reminder();
        reminderRepository.save(newReminder);

        // Act
        Reminder createdReminder = reminderService.createReminder(newReminder);

        // Assert
        assertEquals(newReminder.getReminderID(), createdReminder.getReminderID());
    }

    @Test
    void getAllReminders() {
        // Arrange
        Reminder reminder1 = new Reminder();
        Reminder reminder2 = new Reminder();

        // Act
        List<Reminder> allReminders = reminderService.getAllReminders();
        allReminders.add(reminder1);
        allReminders.add(reminder2);

        // Assert
        assertEquals(2, allReminders.size());
    }

    @Test
    void getReminderById_ExistingId_ReturnsReminder() {
        // Arrange
        Reminder existingReminder = new Reminder();
        reminderRepository.save(existingReminder);

        reminderRepository.findById(existingReminder.getReminderID());

        // Act
        Optional<Reminder> retrievedReminderOptional = reminderService.getReminderById(existingReminder.getReminderID());

        // Assert
        assertTrue(retrievedReminderOptional.isPresent());
        Reminder retrievedReminder = retrievedReminderOptional.get();
        assertEquals(existingReminder.getReminderID(), retrievedReminder.getReminderID());
    }

    // Separate test for getReminderDate()
    @Test
    void getFormattedReminderDate_ReturnsFormattedDate() {
        // Arrange
        Reminder reminder = new Reminder();

        reminder.setReminderDate(LocalDate.now());

        // Act
        String formattedDate = reminder.getFormattedReminderDate();

        // Assert
        assertNotNull(formattedDate, "Formatted date should not be null");
        assertFalse(formattedDate.isEmpty(), "Formatted date should not be empty");
    }


    @Test
    void getReminderById_NonExistingId_ThrowsNotFoundException() {
        // Arrange
        int nonExistingId = 999;
        reminderRepository.findById(nonExistingId);

        // Act and Assert
        assertThrows(NotFoundException.class, () -> reminderService.getReminderById(nonExistingId));
    }



}
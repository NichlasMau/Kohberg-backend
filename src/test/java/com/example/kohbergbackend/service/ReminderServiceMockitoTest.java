package com.example.kohbergbackend.service;

import com.example.kohbergbackend.model.Reminder;
import com.example.kohbergbackend.repository.ReminderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReminderServiceMockitoTest {
    @InjectMocks
    private ReminderService reminderService;

    @Mock
    private ReminderRepository reminderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reminderService = new ReminderService(reminderRepository);
    }
    @Test
    void updateReminder_ExistingId_ReturnsUpdatedReminder() {
        // Arrange
        Reminder existingReminder = new Reminder();
        existingReminder.setReminderID(1);
        existingReminder.setReminderDate(LocalDate.now());
        existingReminder.setCustomerID(123);
        existingReminder.setMessage("Test message");

        Reminder updatedReminderData = new Reminder();
        updatedReminderData.setReminderDate(LocalDate.now().plusDays(1));
        updatedReminderData.setCustomerID(456);
        updatedReminderData.setMessage("Updated message");

        when(reminderRepository.findById(existingReminder.getReminderID())).thenReturn(Optional.of(existingReminder));
        when(reminderRepository.save(Mockito.any(Reminder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Reminder updatedReminder = reminderService.updateReminder(existingReminder.getReminderID(), updatedReminderData);

        // Assert
        assertEquals(updatedReminderData.getReminderDate(), updatedReminder.getReminderDate());
        assertEquals(updatedReminderData.getCustomerID(), updatedReminder.getCustomerID());
        assertEquals(updatedReminderData.getMessage(), updatedReminder.getMessage());
    }

    @Test
    void deleteReminder_ExistingId_ReminderIsDeleted() {
        // Arrange
        Reminder existingReminder = new Reminder();
        existingReminder.setReminderID(1);

        when(reminderRepository.findById(existingReminder.getReminderID())).thenReturn(Optional.of(existingReminder));

        // Act
        reminderService.deleteReminder(existingReminder.getReminderID());

        // Assert
        Mockito.verify(reminderRepository, Mockito.times(1)).deleteById(existingReminder.getReminderID());
    }

    @Test
    void sendReminderEmails_ValidData_EmailsSent() {

        // Act
        reminderService.sendReminderEmails();

    }

}

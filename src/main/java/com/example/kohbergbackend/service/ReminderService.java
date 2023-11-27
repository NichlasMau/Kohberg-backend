package com.example.kohbergbackend.service;

import com.example.kohbergbackend.exception.NotFoundException;
import com.example.kohbergbackend.model.Reminder;
import com.example.kohbergbackend.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    private ReminderRepository reminderRepository;

    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    // Opret en påmindelse
    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    // Hent alle påmindelser
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    // Hent en påmindelse baseret på ID
    public Optional<Reminder> getReminderById(int id) {
        return reminderRepository.findById(id);
    }

    // Opdater en påmindelse
    public Reminder updateReminder(int id, Reminder updatedReminder) {
        return reminderRepository.findById(id)
                .map(existingReminder -> {
                    if (updatedReminder.getReminderDate() != null) {
                        existingReminder.setReminderDate(updatedReminder.getReminderDate());
                    }

                    if (updatedReminder.getCostumerID() != 0) {
                        existingReminder.setCostumerID(updatedReminder.getCostumerID());
                    }

                    if (updatedReminder.getMessage() != null) {
                        existingReminder.setMessage(updatedReminder.getMessage());
                    }

                    return reminderRepository.save(existingReminder);
                })
                .orElse(null);
    }



    // Slet en påmindelse baseret på ID
    public void deleteReminder(int id) {
        reminderRepository.deleteById(id);
    }



}


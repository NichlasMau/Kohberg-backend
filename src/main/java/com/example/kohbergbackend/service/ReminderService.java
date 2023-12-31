package com.example.kohbergbackend.service;

import com.example.kohbergbackend.exception.NotFoundException;
import com.example.kohbergbackend.model.Reminder;
import com.example.kohbergbackend.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    private ReminderRepository reminderRepository;

    @Autowired
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
        Optional<Reminder> reminderOptional = reminderRepository.findById(id);
        return Optional.ofNullable(reminderOptional.orElseThrow(() -> new NotFoundException("Reminder not found with id: " + id)));
    }


    // Opdater en påmindelse
    public Reminder updateReminder(int id, Reminder updatedReminder) {
        return reminderRepository.findById(id)
                .map(existingReminder -> {
                    if (updatedReminder.getReminderDate() != null) {
                        existingReminder.setReminderDate(updatedReminder.getReminderDate());
                    }

                    if (updatedReminder.getCustomerID() != 0) {
                        existingReminder.setCustomerID(updatedReminder.getCustomerID());
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

    //Reminder automatisk til kundens email på en oprettet deadline
    //Felt 1 (Minut), Felt 2 (Time), Felt 3 (Dag i måneden)
    //Felt 4 (Måned), Felt 5 (Dag i ugen), Felt 6 (År)
    @Scheduled(cron = "0 0 12 * * ?") // Eksempel: Udføres hver dag kl. 12:00
    public void sendReminderEmails() {


    }
}


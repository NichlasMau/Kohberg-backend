package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.dto.ReminderDTO;
import com.example.kohbergbackend.model.Reminder;
import com.example.kohbergbackend.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/reminder")
public class ReminderController {

    private ReminderService reminderService;

    @Autowired
    public ReminderController(ReminderService reminderService){
        this.reminderService = reminderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.createReminder(reminder);
        return new ResponseEntity<>(createdReminder, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reminder>> getAllReminders() {
        List<Reminder> reminders = reminderService.getAllReminders();
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    @GetMapping("/{reminderId}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable int reminderId) {
        Optional<Reminder> user = reminderService.getReminderById(reminderId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{reminderId}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable int reminderId, @RequestBody Reminder updatedReminderData) {
        Reminder updatedReminder = reminderService.updateReminder(reminderId, updatedReminderData);
        return updatedReminder != null ?
                new ResponseEntity<>(updatedReminder, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-reminder/{reminderID}")
    public ResponseEntity<Void> deleteReminder(@PathVariable int reminderID) {
        System.out.println("Deleting reminder with ID: " + reminderID);
        reminderService.deleteReminder(reminderID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
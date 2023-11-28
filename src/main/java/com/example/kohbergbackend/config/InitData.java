package com.example.kohbergbackend.config;

import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.User;
import com.example.kohbergbackend.repository.UserRepository;
import com.example.kohbergbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitData implements CommandLineRunner {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public InitData(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Opret en ny kunde
        User newUser = new Customer();
        newUser.setName("John Doe");
        newUser.setBirthday(LocalDate.parse("2001-01-01"));
        newUser.setCreationYear(LocalDate.parse("2022-01-01"));

        // Gem kunden ved hjælp af CostumerService
        User createdUser = userService.createUser(newUser);

        //Gem den nye costumer i databasen
        userRepository.save(createdUser);

        // Du kan gøre noget med den oprettede kunde, hvis det er nødvendigt
        System.out.println("Oprettet kunde med ID: " + createdUser.getUserID());
    }
}
package com.example.kohbergbackend.config;

import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.User;
import com.example.kohbergbackend.repository.UserRepository;
import com.example.kohbergbackend.service.CustomerService;
import com.example.kohbergbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitData implements CommandLineRunner {

    private final CustomerService customerService;
    private final UserRepository userRepository;

    @Autowired
    public InitData(CustomerService customerService, UserRepository userRepository) {
        this.customerService = customerService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Opret en ny kunde
        Customer newUser = new Customer();
        newUser.setName("John Doe");
        newUser.setBirthday(LocalDate.parse("2001-01-01"));
        newUser.setCreationYear(LocalDate.parse("2022-01-01"));

        // Gem kunden ved hjælp af CostumerService
        Customer createdUser = customerService.createCostumer(newUser);

        //Gem den nye costumer i databasen
        customerService.createCostumer(createdUser);

        // Du kan gøre noget med den oprettede kunde, hvis det er nødvendigt
        System.out.println("Oprettet kunde med ID: " + createdUser.getCostumerID());
    }
}
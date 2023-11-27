package com.example.kohbergbackend.config;

import com.example.kohbergbackend.model.Costumer;
import com.example.kohbergbackend.repository.CostumerRepository;
import com.example.kohbergbackend.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitData implements CommandLineRunner {

    private final CostumerService costumerService;
    private final CostumerRepository costumerRepository;

    @Autowired
    public InitData(CostumerService costumerService, CostumerRepository costumerRepository) {
        this.costumerService = costumerService;
        this.costumerRepository = costumerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Opret en ny kunde
        Costumer newCostumer = new Costumer();
        newCostumer.setName("John Doe");
        newCostumer.setBirthday(LocalDate.parse("2001-01-01"));
        newCostumer.setCreationYear(LocalDate.parse("2022-01-01"));

        // Gem kunden ved hjælp af CostumerService
        Costumer createdCostumer = costumerService.createCostumer(newCostumer);

        //Gem den nye costumer i databasen
        costumerRepository.save(createdCostumer);

        // Du kan gøre noget med den oprettede kunde, hvis det er nødvendigt
        System.out.println("Oprettet kunde med ID: " + createdCostumer.getCostumerID());
    }
}
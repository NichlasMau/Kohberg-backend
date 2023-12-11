package com.example.kohbergbackend.config;

import com.example.kohbergbackend.dto.*;
import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.Sale;
import com.example.kohbergbackend.model.Salesperson;
import com.example.kohbergbackend.model.User;
import com.example.kohbergbackend.repository.UserRepository;
import com.example.kohbergbackend.service.CustomerService;
import com.example.kohbergbackend.service.SaleService;
import com.example.kohbergbackend.service.SalespersonService;
import com.example.kohbergbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private final CustomerService customerService;
    private final SaleService saleService;
    private final SaleConverter saleConverter;
    private final SalespersonService salespersonService;
    private final CustomerConverter customerConverter;



    @Autowired
    public InitData(CustomerService customerService,
                    SaleService saleService, SaleConverter saleConverter,
                    SalespersonService salespersonService, CustomerConverter customerConverter) {
        this.customerService = customerService;
        this.saleService = saleService;
        this.saleConverter = saleConverter;
        this.salespersonService = salespersonService;
        this.customerConverter = customerConverter;
    }

    @Override
    public void run(String... args) throws Exception {
        // Opret en ny kunde
        CustomerDTO newUser = new CustomerDTO(
                1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01")
        );

        // Gem kunden ved hjælp af CustomerService
        Customer createdUser = customerService.createCustomer(newUser);

        // Du kan gøre noget med den oprettede kunde, hvis det er nødvendigt
        System.out.println("Oprettet kunde med ID: " + createdUser.getCustomerID());

        SaleDTO newSaleDTO = new SaleDTO(1,1, LocalDate.parse("2023-01-01"), "Example Product");

        // Konverter SaleDTO til Sale ved hjælp af SaleConverter
        Sale newSale = saleConverter.toEntity(newSaleDTO);

        // Gem salget ved hjælp af SaleService
        Sale createdSale = saleService.createSale(newSale);

        // Du kan gøre noget med det oprettede salg, hvis det er nødvendigt
        System.out.println("Oprettet salg med ID: " + createdSale.getSaleID());

        Customer customer = customerConverter.toEntity(newUser);

        // Opret en ny SalespersonDTO
        SalespersonDTO newSalespersonDTO = new SalespersonDTO(
                1,
                "Jane Doe",
                LocalDate.parse("1995-05-15"),
                List.of(customer),
                "jane.doe@example.com"
        );


        // Gem Salesperson ved hjælp af SalespersonService
        SalespersonDTO createdSalesperson = salespersonService.createSalesperson(newSalespersonDTO);

        // Du kan gøre noget med den oprettede Salesperson, hvis det er nødvendigt
        System.out.println("Oprettet Salesperson med ID: " + createdSalesperson.id());
    }
}
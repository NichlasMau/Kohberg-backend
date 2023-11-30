package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.dto.CustomerConverter;
import com.example.kohbergbackend.dto.CustomerDTO;
import com.example.kohbergbackend.dto.SalespersonDTO;
import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.repository.SalespersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalespersonControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private SalespersonRepository salespersonRepository;

    @Autowired
    private CustomerConverter customerConverter;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/salesperson";
    }

    @Test
    void getAllSalespersons() {
        ResponseEntity<List<SalespersonDTO>> response = restTemplate.exchange(
                getBaseUrl() + "/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SalespersonDTO>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getSalespersonById() {
        // Assuming you have a salesperson in the database with id 1
        int salespersonId = 1;

        ResponseEntity<SalespersonDTO> response = restTemplate.getForEntity(
                getBaseUrl() + "/" + salespersonId,
                SalespersonDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(salespersonId, response.getBody().id());
    }

    @Test
    void createSalesperson() {

        List<CustomerDTO> customerDTOs = new ArrayList<>();

        // Create the first customer
        CustomerDTO customer1 = new CustomerDTO(
                1,
                "Customer 1",
                "Type 1",
                LocalDate.parse("2000-01-01"),
                "customer1@example.com",
                LocalDate.parse("2022-01-01")
        );
        customerDTOs.add(customer1);

        // Create the second customer
        CustomerDTO customer2 = new CustomerDTO(
                2,
                "Customer 2",
                "Type 2",
                LocalDate.parse("2000-02-02"),
                "customer2@example.com",
                LocalDate.parse("2022-02-02")
        );
        customerDTOs.add(customer2);

        // Convert CustomerDTOs to Customers
        List<Customer> customers = customerDTOs.stream()
                .map(customerConverter::toEntity)
                .collect(Collectors.toList());

        // Now, assuming you have a list of salespersons available
        List<SalespersonDTO> salespersons = new ArrayList<>();

        // Create the salesperson with the list of customers
        SalespersonDTO newSalesperson = new SalespersonDTO(
                1,
                "John Doe",
                LocalDate.parse("2012-12-12"),
                customers,  // This should be the list of customers
                "john.doe@example.com"
        );

        // Add the new salesperson to the list
        salespersons.add(newSalesperson);
        ResponseEntity<SalespersonDTO> response = restTemplate.postForEntity(
                getBaseUrl() + "/create",
                newSalesperson,
                SalespersonDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    @Test
    void updateSalesperson() {
        // Assuming you have a salesperson in the database with id 1
        int salespersonId = 1;
        List<CustomerDTO> customerDTOs = new ArrayList<>();

// Create the first customer
        CustomerDTO customer1 = new CustomerDTO(
                1,
                "Customer 1",
                "Type 1",
                LocalDate.parse("2000-01-01"),
                "customer1@example.com",
                LocalDate.parse("2022-01-01")
        );
        customerDTOs.add(customer1);

// Create the second customer
        CustomerDTO customer2 = new CustomerDTO(
                2,
                "Customer 2",
                "Type 2",
                LocalDate.parse("2000-02-02"),
                "customer2@example.com",
                LocalDate.parse("2022-02-02")
        );
        customerDTOs.add(customer2);

// Convert CustomerDTOs to Customers
        List<Customer> customers = customerDTOs.stream()
                .map(customerConverter::toEntity)
                .collect(Collectors.toList());

// Now, assuming you have a list of salespersons available
        List<SalespersonDTO> salespersons = new ArrayList<>();

        SalespersonDTO updatedSalespersonData = new SalespersonDTO(1,
                "John Doe",
                LocalDate.parse("2012-12-12"),
                customers,  // This should be the list of customers
                "john.doe@example.com");

        ResponseEntity<SalespersonDTO> response = restTemplate.exchange(
                getBaseUrl() + "/update/" + salespersonId,
                HttpMethod.PUT,
                new HttpEntity<>(updatedSalespersonData),
                SalespersonDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void deleteSalesperson() {
        // Assuming you have a salesperson in the database with id 1
        int salespersonId = 1;

        ResponseEntity<Void> response = restTemplate.exchange(
                getBaseUrl() + "/delete/" + salespersonId,
                HttpMethod.DELETE,
                null,
                Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
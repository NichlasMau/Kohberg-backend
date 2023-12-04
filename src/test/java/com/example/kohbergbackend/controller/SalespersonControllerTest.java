package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.dto.CustomerConverter;
import com.example.kohbergbackend.dto.CustomerDTO;
import com.example.kohbergbackend.dto.SalespersonConverter;
import com.example.kohbergbackend.dto.SalespersonDTO;
import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.repository.CustomerRepository;
import com.example.kohbergbackend.repository.SalespersonRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SalespersonControllerTest {

    @Autowired
    private SalespersonRepository salespersonRepository;
    @Autowired
    private SalespersonController salespersonController;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private SalespersonConverter salespersonConverter;
    @Autowired
    private CustomerRepository customerRepository;


    @Test
    void getAllSalespersons() {
        ResponseEntity<List<SalespersonDTO>> response = salespersonController.getAllSalespersons();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(2)
    void getSalespersonById() {
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

        // Save customers to the database
        customerRepository.save(customerConverter.toEntity(customer1));
        customerRepository.save(customerConverter.toEntity(customer2));

        // Convert CustomerDTOs to Customers
        List<Customer> customers = customerDTOs.stream()
                .map(customerConverter::toEntity)
                .collect(Collectors.toList());

        // Create the salesperson with the list of customers
        SalespersonDTO newSalesperson = new SalespersonDTO(
                1,
                "John Doe",
                LocalDate.parse("2012-12-12"),
                customers,
                "john.doe@example.com"
        );

        // Save the salesperson to the database
        salespersonRepository.save(salespersonConverter.toEntity(newSalesperson));

        // Retrieve the salesperson by ID
        ResponseEntity<SalespersonDTO> response = salespersonController.getSalespersonById(newSalesperson.id());

        // Assert that the response is successful (HTTP Status 200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert that the retrieved salesperson has the expected ID
        SalespersonDTO retrievedSalesperson = response.getBody();
        assertNotNull(retrievedSalesperson);
        assertEquals(newSalesperson.id(), retrievedSalesperson.id());
    }

    @Test
    @Order(1)
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

        //Save customers to database
        customerRepository.save(customerConverter.toEntity(customer1));
        customerRepository.save(customerConverter.toEntity(customer2));

        // Convert CustomerDTOs to Customers
        List<Customer> customers = customerDTOs.stream()
                .map(customerConverter::toEntity)
                .collect(Collectors.toList());

        //Create the SalespersonDTO to be sent in the request
        SalespersonDTO requestSalespersonDTO = new SalespersonDTO(
                1,
                "John Doe",
                LocalDate.parse("2012-12-12"),
                customers,
                "john.doe@example.com"
        );

        // Perform the request to create the salesperson
        ResponseEntity<SalespersonDTO> response = salespersonController.createSalesperson(requestSalespersonDTO);

        // Assert that the response is successful (HTTP Status 200)
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Assert that the created salesperson matches the expected salesperson DTO
        SalespersonDTO createdSalespersonDTO = response.getBody();
        assertNotNull(createdSalespersonDTO);
        assertEquals(requestSalespersonDTO.id(), createdSalespersonDTO.id());
        assertEquals(requestSalespersonDTO.name(), createdSalespersonDTO.name());
        // Add additional assertions for other fields

        // Save the expected and actual SalespersonDTO objects as strings
        String expectedAsString = requestSalespersonDTO.toString();
        String actualAsString = createdSalespersonDTO.toString();

        // Assert that the string representations are equal
        assertEquals(expectedAsString, actualAsString);
    }

    @Test
    @Order(3)
    void createAndThenUpdateSalesperson() {
        int salespersonId = 1;

        // Create the first customer
        CustomerDTO customer1 = new CustomerDTO(
                1,
                "Customer 1",
                "Type 1",
                LocalDate.parse("2000-01-01"),
                "customer1@example.com",
                LocalDate.parse("2022-01-01")
        );

        // Create the second customer
        CustomerDTO customer2 = new CustomerDTO(
                2,
                "Customer 2",
                "Type 2",
                LocalDate.parse("2000-02-02"),
                "customer2@example.com",
                LocalDate.parse("2022-02-02")
        );
        //Save customers to database
        customerRepository.save(customerConverter.toEntity(customer1));
        customerRepository.save(customerConverter.toEntity(customer2));

        // Convert CustomerDTOs to Customers
        List<Customer> customers = Stream.of(customer1, customer2)
                .map(customerConverter::toEntity)
                .collect(Collectors.toList());

        // Create the initial salesperson data
        SalespersonDTO initialSalespersonDTO = new SalespersonDTO(
                1,
                "Initial Name",
                LocalDate.parse("2010-10-10"),
                Collections.emptyList(),
                "initial@example.com"
        );

        salespersonRepository.save(salespersonConverter.toEntity(initialSalespersonDTO));

        // Perform the request to create the salesperson
        ResponseEntity<SalespersonDTO> createdSalespersonResponse = salespersonController.createSalesperson(initialSalespersonDTO);

        // Assert that the response is successful (HTTP Status 201)
        assertEquals(HttpStatus.CREATED, createdSalespersonResponse.getStatusCode());

        // Get the created salesperson from the response
        SalespersonDTO createdSalesperson = createdSalespersonResponse.getBody();
        assertNotNull(createdSalesperson);

        // Update the salesperson data
        SalespersonDTO updatedSalespersonDTO = new SalespersonDTO(
                1,
                "John Doe",
                LocalDate.parse("2012-12-12"),
                customers,
                "john.doe@example.com"
        );

        salespersonRepository.save(salespersonConverter.toEntity(updatedSalespersonDTO));

        // Perform the request to update the salesperson
        ResponseEntity<SalespersonDTO> updatedSalespersonResponse = salespersonController.updateSalesperson(salespersonId, updatedSalespersonDTO);

        // Assert that the update response is successful (HTTP Status 200)
        assertEquals(HttpStatus.OK, updatedSalespersonResponse.getStatusCode());

        // Get the updated salesperson from the response
        SalespersonDTO updatedSalesperson = updatedSalespersonResponse.getBody();
        assertNotNull(updatedSalesperson);

        // Assert that the updated salesperson matches the expected updated data
        assertEquals(updatedSalespersonDTO.id(), updatedSalesperson.id());
        assertEquals(updatedSalespersonDTO.name(), updatedSalesperson.name());
    }

    @Test
    @Order(4)
    void deleteSalesperson() {
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

        //Save customers to database
        customerRepository.save(customerConverter.toEntity(customer1));
        customerRepository.save(customerConverter.toEntity(customer2));

        // Convert CustomerDTOs to Customers
        List<Customer> customers = customerDTOs.stream()
                .map(customerConverter::toEntity)
                .collect(Collectors.toList());

        SalespersonDTO salesperson = new SalespersonDTO(1, "John Doe",
                LocalDate.now(), customers, "john.doe@exemple.com" ) ;

        salespersonRepository.save(salespersonConverter.toEntity(salesperson));

        salespersonController.deleteSalesperson(salesperson.id());


        // Delete the salesperson
        salespersonController.deleteSalesperson(salesperson.id());

        // Verify that attempting to retrieve the deleted salesperson returns a not found status
        ResponseEntity<SalespersonDTO> response = salespersonController.getSalespersonById(salesperson.id());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
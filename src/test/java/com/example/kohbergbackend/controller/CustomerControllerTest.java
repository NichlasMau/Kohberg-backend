package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.dto.CustomerConverter;
import com.example.kohbergbackend.dto.CustomerDTO;
import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;

    @Test
    void createCostumer() {
        CustomerDTO customer = new CustomerDTO(1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01"));
        customerController.createCustomer(customer);

        int id = customer.customerID();

        assertEquals(1, id);
    }

    @Test
    void getAllCustomers() {
        // Arrange
        Customer customer1 = new Customer(1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01"));
        Customer customer2 = new Customer(1,
                "Jane Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01"));

        customerRepository.saveAll(Arrays.asList(customer1, customer2));

        // Act
        ResponseEntity<List<CustomerDTO>> responseEntity = customerController.getAllCustomers();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void getCustomerById() {
        // Arrange
        Customer customer1 = new Customer(1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01"));
        int id = customer1.getCustomerID();

        assertEquals(1, id);

    }

    @Test
    void updateCustomer() {
        // Arrange
        Customer customer1 = new Customer(1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01"));

        customerRepository.save(customer1);

        // Update customer information
        CustomerDTO updatedCustomer = new CustomerDTO(1,
                "Jane Doe",  // Updated name to "Jane Doe"
                "Customer",
                LocalDate.parse("2001-01-01"),
                "jane.doe@example.com",  // Updated email
                LocalDate.parse("2022-01-01"));

        // Act
        ResponseEntity<CustomerDTO> responseEntity = customerController.updateCustomer(customer1.getCustomerID(), updatedCustomer);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Optional: Check if the returned DTO contains the updated information
        CustomerDTO updatedCustomerDTO = customerRepository.findById(customer1.getCustomerID())
                .map(customerConverter::toDTO)
                .orElse(null);

        assertNotNull(updatedCustomerDTO);
        assertEquals(updatedCustomer.name(), updatedCustomerDTO.name());
        assertEquals(updatedCustomer.email(), updatedCustomerDTO.email());
    }


    @Test
    void deleteCustomer() {
        // Arrange
        Customer customer1 = new Customer(1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01"));

        customerRepository.save(customer1);

        // Act
        ResponseEntity<Void> responseEntity = customerController.deleteCustomer(customer1.getCustomerID());

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Fetch the customer again from the repository
        Customer deletedCustomer = customerRepository.findById(customer1.getCustomerID()).orElse(null);

        // Check if the customer is null, indicating successful deletion
        assertNull(deletedCustomer);
    }
}
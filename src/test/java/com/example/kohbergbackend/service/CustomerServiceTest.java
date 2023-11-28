package com.example.kohbergbackend.service;

import com.example.kohbergbackend.dto.CustomerConverter;
import com.example.kohbergbackend.dto.CustomerDTO;
import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Test
    void createCustomer() {
        // Arrange
        CustomerDTO newCustomerDTO = new CustomerDTO(
                1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01")
        );

        // Act
        Customer createdCustomer = customerService.createCustomer(newCustomerDTO);

        // Assert
        assertNotNull(createdCustomer);
        assertEquals("John Doe", createdCustomer.getName());
        assertEquals("john.doe@example.com", createdCustomer.getEmail());
    }

    @Test
    void getAllCustomers() {
        // Arrange
        CustomerDTO newCustomerDTO = new CustomerDTO(
                1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01")
        );
        // Save a customer
        customerService.createCustomer(newCustomerDTO);

        // Act
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

        // Assert
        assertFalse(allCustomers.isEmpty());
    }

    @Test
    void getCustomerById() {
        CustomerDTO newCustomerDTO = new CustomerDTO(
                1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01")
        );
        // Save a customer
        Customer createdCustomer = customerService.createCustomer(newCustomerDTO);

        // Act
        Optional<CustomerDTO> retrievedCustomer = customerService.getCustomerById(createdCustomer.getCostumerID());

        // Assert
        assertTrue(retrievedCustomer.isPresent());
        assertEquals("John Doe", retrievedCustomer.get().name());
    }

    @Test
    void updateCustomer() {
        // Arrange
        CustomerDTO newCustomerDTO = new CustomerDTO(
                1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01")
        );

        // Save a customer
        Customer createdCustomer = customerService.createCustomer(newCustomerDTO);

        // Act
        createdCustomer.setName("Updated Name");
        CustomerDTO updatedCustomer = customerService.updateCustomer(createdCustomer.getCostumerID(), customerConverter.toDTO(createdCustomer));

        // Assert
        assertNotNull(updatedCustomer);
        assertEquals("Updated Name", updatedCustomer.name());
    }

    @Test
    void deleteCustomer() {
        // Arrange
        CustomerDTO newCustomerDTO = new CustomerDTO(
                1,
                "John Doe",
                "Customer",
                LocalDate.parse("2001-01-01"),
                "john.doe@example.com",
                LocalDate.parse("2022-01-01")
        );
        // Save a customer
        Customer createdCustomer = customerService.createCustomer(newCustomerDTO);

        // Act
        customerService.deleteCustomer(createdCustomer.getCostumerID());

        // Assert
        assertFalse(customerService.getCustomerById(createdCustomer.getCostumerID()).isPresent());
    }
}
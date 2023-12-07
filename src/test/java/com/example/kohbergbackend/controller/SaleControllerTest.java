package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.dto.*;
import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.Sale;
import com.example.kohbergbackend.repository.CustomerRepository;
import com.example.kohbergbackend.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SaleControllerTest {

    @Autowired
    private SaleController salecontroller;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleConverter saleConverter;


    @Test
    void createSale() {
        CustomerDTO customer = new CustomerDTO(1, "Amir", "Customer", LocalDate.of(2001, 1,1), "amir@gmail.com", LocalDate.of(2023, 6,12));
        Sale sale = new Sale(1, 1, LocalDate.of(2023,1,1), "solsikkerugbrød");
        customerRepository.save(customerConverter.toEntity(customer));

        salecontroller.createSale(sale);
        saleRepository.save(sale);

        int id = sale.getSaleID();

        int customerId = customer.customerID();

        assertEquals(1,id);
        assertEquals(1, customerId);
    }

    @Test
    void getAllSales() {
        // Arrange
        Sale sale1 = new Sale(1,1, LocalDate.of(2023,5,12), "solsikkerugbrød");
        Sale sale2 = new Sale(2,2, LocalDate.of(2023,6,12), "kærnerugbrød");
        Sale sale3 = new Sale(3,3, LocalDate.of(2023,7,12), "krydreboller");

        saleRepository.saveAll(Arrays.asList(sale1,sale2,sale3));

        //Act
        ResponseEntity<List<Sale>> responseEntity = salecontroller.getAllSales();

        //Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(3, responseEntity.getBody().size());
    }

    @Test
    void getCostumerById() {

        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();

        CustomerDTO customer1 = new CustomerDTO(7,
                "amir",
                "Customer",
                LocalDate.of(2001,1,1),
                "amir@gmail.com",
                LocalDate.of(2023,6,12));

        customers.add(customerConverter.toEntity(customer1));

        SalespersonDTO salespersonDTO = new SalespersonDTO(1,
                "amir",
                LocalDate.of(2001,1,1),
                customers,
                "amir@gmail.com");

        SaleDTO saleDTO = new SaleDTO(1,
                7,
                LocalDate.of(2023, 6,12),
                "solsikkerugbrød");

        customer.setCustomerID(7);
        customer.setSale(saleConverter.toEntity(saleDTO));


        Sale sale = customer.getSale();

        int id = sale.getSaleID();

             assertEquals(1, id);
    }

    @Test
    void updateSale() {

        Sale sale1 = new Sale(1,
                1,
                LocalDate.of(2023,5,12),
                "solsikkerugbrød");

        saleRepository.save(sale1);

        //Update sale information

        Sale updatedSale = new Sale(1,
                1,
                LocalDate.of(2023,5,12),
                "burgerboller" // product updated

        );

        // Act
        ResponseEntity<Sale> responseEntity = salecontroller.updateSale(sale1.getSaleID(), updatedSale);

        //Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(updatedSale);
        assertEquals(updatedSale.getProduct(), updatedSale.getProduct());
    }

    @Test
    void deleteCostumer() {
        //Arrange
        Sale sale1 = new Sale(1,
                1,
                LocalDate.of(2023,5,12),
                "burgerboller"
        );

        saleRepository.save(sale1);


        //Act
        ResponseEntity<Void> responseEntity = salecontroller.deleteCostumer(sale1.getSaleID());

        //Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Fetch the Sale again from repository
        Sale deletedSale = saleRepository.findById(sale1.getSaleID()).orElse(null);

        // Check if the sale is null, indicating successful deletion
        assertNull(deletedSale);



    }
}
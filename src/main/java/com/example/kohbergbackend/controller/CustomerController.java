package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.dto.CustomerDTO;
import com.example.kohbergbackend.exception.NotFoundException;
import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int customerId) {
        Optional<CustomerDTO> user = customerService.getCustomerById(customerId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int customerId, @RequestBody CustomerDTO updatedCustomerData) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, updatedCustomerData);
        return updatedCustomer != null ?
                new ResponseEntity<>(updatedCustomer, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-customer/{customerID}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerID) {
        System.out.println("Deleting customer with ID: " + customerID);
        customerService.deleteCustomer(customerID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.dto.CustomerDTO;
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

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCostumer(@RequestBody CustomerDTO customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAllCostumers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CustomerDTO> getCostumerById(@PathVariable int userId) {
        Optional<CustomerDTO> user = customerService.getCustomerById(userId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<CustomerDTO> updateCostumer(@PathVariable int userId, @RequestBody CustomerDTO updatedCustomerData) {
        CustomerDTO updatedCostumer = customerService.updateCustomer(userId, updatedCustomerData);
        return updatedCostumer != null ?
                new ResponseEntity<>(updatedCostumer, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{costumerId}")
    public ResponseEntity<Void> deleteCostumer(@PathVariable int costumerId) {
        customerService.deleteCustomer(costumerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
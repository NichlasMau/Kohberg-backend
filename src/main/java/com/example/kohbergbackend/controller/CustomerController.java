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

    @GetMapping("/{costumerId}")
    public ResponseEntity<CustomerDTO> getCostumerById(@PathVariable int costumerId) {
        Optional<CustomerDTO> user = customerService.getCustomerById(costumerId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{costumerId}")
    public ResponseEntity<CustomerDTO> updateCostumer(@PathVariable int costumerId, @RequestBody CustomerDTO updatedCustomerData) {
        CustomerDTO updatedCostumer = customerService.updateCustomer(costumerId, updatedCustomerData);
        return updatedCostumer != null ?
                new ResponseEntity<>(updatedCostumer, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-customer/{customerID}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerID) {
        System.out.println("Deleting customer with ID: " + customerID);
        customerService.deleteCustomer(customerID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
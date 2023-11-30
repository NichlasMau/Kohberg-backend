package com.example.kohbergbackend.repository;

import com.example.kohbergbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    void deleteByCustomerID(int customerId);
}

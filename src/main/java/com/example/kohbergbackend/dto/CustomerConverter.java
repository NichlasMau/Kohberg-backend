package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    public Customer toEntity(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.customerID(),
                customerDTO.name(),
                "Customer",
                customerDTO.birthday(),
                customerDTO.email(),
                customerDTO.creationYear()
        );
    }

    public List<CustomerDTO> toDTOList(List<Customer> customers) {
        return customers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
                customer.getCustomerID(),
                customer.getName(),
                customer.getRole(),
                customer.getBirthday(),
                customer.getEmail(),
                customer.getCreationYear()
        );
    }

}

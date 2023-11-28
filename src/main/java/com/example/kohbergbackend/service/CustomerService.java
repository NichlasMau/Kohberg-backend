package com.example.kohbergbackend.service;

import com.example.kohbergbackend.dto.CustomerConverter;
import com.example.kohbergbackend.dto.CustomerDTO;
import com.example.kohbergbackend.exception.NotFoundException;
import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private CustomerRepository customerRepository;
    private CustomerConverter customerConverter;

    /**
     * Constructor for CustomerService.
     *
     * @param customerRepository The repository for managing Customer entities.
     * @param customerConverter  The converter for converting between Customer and CustomerDTO.
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    /**
     * Creates a new Customer.
     *
     * @param customerDTO The CustomerDTO containing data for creating the Customer.
     * @return The created Customer entity.
     */
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerConverter.toEntity(customerDTO);
        return customerRepository.save(customer);
    }

    /**
     * Retrieves all Customers.
     *
     * @return A list of all Customer entities.
     */
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerConverter.toDTOList(customers);
    }


    /**
     * Retrieves a Customer by its unique identifier.
     *
     * @param customerId The unique identifier of the Customer to retrieve.
     * @return An Optional containing the retrieved CustomerDTO, or an empty Optional if not found.
     */
    public Optional<CustomerDTO> getCustomerById(int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        return customerOptional.map(customerConverter::toDTO);
    }

    /**
     * Updates an existing Customer.
     *
     * @param customerId          The unique identifier of the Customer to update.
     * @param updatedCustomerData The updated CustomerDTO data.
     * @return The updated CustomerDTO, or null if the Customer was not found.
     */
    public CustomerDTO updateCustomer(int customerId, CustomerDTO updatedCustomerData) {
        return customerRepository.findById(customerId)
                .map(existingCustomer -> {
                    Customer updatedCustomer = customerConverter.toEntity(updatedCustomerData);
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setBirthday(updatedCustomer.getBirthday());
                    existingCustomer.setEmail(updatedCustomer.getEmail());
                    existingCustomer.setCreationYear(updatedCustomer.getCreationYear());
                    return customerConverter.toDTO(customerRepository.save(existingCustomer));
                })
                .orElse(null);
    }

    /**
     * Deletes a Customer by its unique identifier.
     *
     * @param customerId The unique identifier of the Customer to delete.
     */

    public void deleteCustomer(int customerId) {
        customerRepository.deleteByCostumerID(customerId);
    }
}
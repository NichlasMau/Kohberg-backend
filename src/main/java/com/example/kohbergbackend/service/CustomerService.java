package com.example.kohbergbackend.service;

import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository costumerRepository;

    /**
     * Constructor for CostumerService.
     *
     * @param costumerRepository The repository for managing Costumer entities.
     */
    @Autowired
    public CustomerService(CustomerRepository costumerRepository) {
        this.costumerRepository =  costumerRepository;
    }

    /**
     * Creates a new Costumer.
     *
     * @param costumer The Costumer entity to be created.
     * @return The created Costumer entity.
     */
    public Customer createCostumer(Customer costumer) {
        return costumerRepository.save(costumer);
    }

    /**
     * Retrieves all Costumers.
     *
     * @return A list of all Costumer entities.
     */
    public List<Customer> getAllCostumers() {
        return costumerRepository.findAll();
    }

    /**
     * Retrieves a Costumer by its unique identifier.
     *
     * @param costumerId The unique identifier of the Costumer to retrieve.
     * @return An Optional containing the retrieved Costumer, or an empty Optional if not found.
     */
    public Optional<Customer> getCostumerById(int costumerId) {
        return costumerRepository.findById(costumerId);
    }

    /**
     * Updates an existing Costumer.
     *
     * @param costumerId The unique identifier of the Costumer to update.
     * @return The updated Costumer entity, or null if the Costumer was not found.
     */
    public Customer updateCostumer(int costumerId, Customer updatedCostumerData) {
        return costumerRepository.findById(costumerId)
                .map(existingCostumer -> {
                    if (updatedCostumerData.getName() != null) {
                        existingCostumer.setName(updatedCostumerData.getName());
                    }
                    if (updatedCostumerData.getBirthday() != null) {
                        existingCostumer.setBirthday(updatedCostumerData.getBirthday());
                    }
                    if (updatedCostumerData.getCreationYear() != null) {
                        existingCostumer.setCreationYear(updatedCostumerData.getCreationYear());
                    }

                    return costumerRepository.save(existingCostumer);
                })
                .orElse(null);
    }

    /**
     * Deletes a Costumer by its unique identifier.
     *
     * @param costumerId The unique identifier of the Costumer to delete.
     */
    public void deleteCostumer(int costumerId) {
        costumerRepository.deleteById(costumerId);
    }
}


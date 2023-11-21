package com.example.kohbergbackend.service;

import com.example.kohbergbackend.model.Costumer;
import com.example.kohbergbackend.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Customer entities.
 */
@Service
public class CostumerService {

    private CostumerRepository costumerRepository;

    /**
     * Constructor for CostumerService.
     *
     * @param costumerRepository The repository for managing Costumer entities.
     */
    @Autowired
    public CostumerService(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    /**
     * Creates a new Costumer.
     *
     * @param costumer The Costumer entity to be created.
     * @return The created Costumer entity.
     */
    public Costumer createCostumer(Costumer costumer) {
        return costumerRepository.save(costumer);
    }

    /**
     * Retrieves all Costumers.
     *
     * @return A list of all Costumer entities.
     */
    public List<Costumer> getAllCostumers() {
        return costumerRepository.findAll();
    }

    /**
     * Retrieves a Costumer by its unique identifier.
     *
     * @param costumerId The unique identifier of the Costumer to retrieve.
     * @return An Optional containing the retrieved Costumer, or an empty Optional if not found.
     */
    public Optional<Costumer> getCostumerById(int costumerId) {
        return costumerRepository.findById(costumerId);
    }

    /**
     * Updates an existing Costumer.
     *
     * @param costumerId The unique identifier of the Costumer to update.
     * @return The updated Costumer entity, or null if the Costumer was not found.
     */
    public Costumer updateCostumer(int costumerId) {
        if (costumerRepository.existsById(costumerId)) {
            Costumer updatedCostumer = costumerRepository.getById(costumerId);
            return costumerRepository.save(updatedCostumer);
        }
        return null;
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
package com.example.kohbergbackend.service;

import com.example.kohbergbackend.exception.NotFoundException;
import com.example.kohbergbackend.model.Salesperson;
import com.example.kohbergbackend.repository.SalespersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalespersonService {
    private final SalespersonConverter salespersonConverter;
    private final SalespersonRepository salespersonRepository;


    @Autowired
    public SalespersonService(SalespersonConverter salespersonConverter, SalespersonRepository salespersonRepository){
        this.salespersonConverter = salespersonConverter;
        this.salespersonRepository = salespersonRepository;
    }


    /**
     * Retrieves a salesperson DTO by their unique identifier (ID).
     *
     * @param id The ID of the salesperson to retrieve.
     * @return The salesperson DTO if found.
     * @throws NotFoundException if no salesperson is found with the given ID.
     */
    public SalespersonDTO getSalespersonById(int id) {
        Optional<Salesperson> salespersonOptional = salespersonRepository.findById(id);
        if (salespersonOptional.isPresent()) {
            return salespersonConverter.toDTO(salespersonOptional.get());
        } else {
            throw new NotFoundException("Salesperson not found with Id " + id);
        }
    }

    /**
     * Retrieves a list of all salespersons as DTOs.
     *
     * @return A list containing all salesperson DTOs.
     */
    public List<SalespersonDTO> getAllSalesperson(){
        List<Salesperson> salespersons = salespersonRepository.findAll();
        return salespersons.stream()
                .map(salespersonConverter::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Updates the information of a specific salesperson based on their ID.
     *
     * @param id The ID of the salesperson to be updated.
     * @param salespersonDTO The DTO containing the updated salesperson information.
     * @return The updated salesperson DTO if the salesperson is found and updated.
     * @throws NotFoundException if no salesperson is found with the given ID.
     */
    public SalespersonDTO updateSalesperson(int id, SalespersonDTO salespersonDTO){
        Optional<Salesperson> existingSalesperson = salespersonRepository.findById(id);
        if (existingSalesperson.isPresent()){
            Salesperson salespersonToUpdate = salespersonConverter.toEntity(salespersonDTO);
            salespersonToUpdate.setUserID(id);
            Salesperson savedSalesperson = salespersonRepository.save(salespersonToUpdate);
            return salespersonConverter.toDTO(savedSalesperson);
        } else {
            throw new NotFoundException("Salesperson not found with id " + id);
        }
    }

    /**
     * Creates a new salesperson and returns the created salesperson as a DTO.
     *
     * @param salespersonDTO The DTO containing the information of the salesperson to be created.
     * @return The created salesperson DTO.
     */
    public SalespersonDTO createSalesperson(SalespersonDTO salespersonDTO){
        Salesperson salespersonToSave = salespersonConverter.toEntity(salespersonDTO);
        salespersonToSave.setUserID(0);
        Salesperson savedSalesperson = salespersonRepository.save(salespersonToSave);
        return salespersonConverter.toDTO(savedSalesperson);
    }

    /**
     * Deletes a salesperson based on their unique identifier (ID).
     *
     * @param id The ID of the salesperson to be deleted.
     * @throws NotFoundException if no salesperson is found with the given ID.
     */
    public void deleteSalesperson(int id) {
        Optional<Salesperson> salesperson = salespersonRepository.findById(id);
        if (salesperson.isPresent()){
            salespersonRepository.deleteById(id);
        } else {
            throw new NotFoundException("Salesperson not found with id: " + id);
        }
    }

}

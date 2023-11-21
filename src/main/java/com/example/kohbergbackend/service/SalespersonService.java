package com.example.kohbergbackend.service;

import com.example.kohbergbackend.dto.SalespersonConverter;
import com.example.kohbergbackend.dto.SalespersonDTO;
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


    public SalespersonDTO getSalespersonById(int id) {
        Optional<Salesperson> salespersonOptional = salespersonRepository.findById(id);
        if (salespersonOptional.isPresent()) {
            return salespersonConverter.toDTO(salespersonOptional.get());
        } else {
            throw new NotFoundException("Salesperson not found with Id " + id);
        }
    }

    public List<SalespersonDTO> getAllSalesperson(){
        List<Salesperson> salespersons = salespersonRepository.findAll();
        return salespersons.stream()
                .map(salespersonConverter::toDTO)
                .collect(Collectors.toList());
    }

    public SalespersonDTO updateSalesperson(int id, SalespersonDTO salespersonDTO){
        Optional<Salesperson> existingSalesperson = salespersonRepository.findById(id);
        if (existingSalesperson.isPresent()){
            Salesperson salespersonToUpdate = salespersonConverter.toEntity(salespersonDTO);
            salespersonToUpdate.setId(id);
            Salesperson savedSalesperson = salespersonRepository.save(salespersonToUpdate);
            return salespersonConverter.toDTO(savedSalesperson);
        } else {
            throw new NotFoundException("Salesperson not found with id " + id);
        }
    }

    public SalespersonDTO createSalesperson(SalespersonDTO salespersonDTO){
        Salesperson salespersonToSave = salespersonConverter.toEntity(salespersonDTO);
        salespersonToSave.setId(0);
        Salesperson savedSalesperson = salespersonRepository.save(salespersonToSave);
        return salespersonConverter.toDTO(savedSalesperson);
    }

    public void deleteSalesperson(int id) {
        Optional<Salesperson> salesperson = salespersonRepository.findById(id);
        if (salesperson.isPresent()){
            salespersonRepository.deleteById(id);
        } else {
            throw new NotFoundException("Salesperson not found with id: " + id);
        }
    }
}

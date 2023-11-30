package com.example.kohbergbackend.service;

import com.example.kohbergbackend.dto.SalespersonConverter;
import com.example.kohbergbackend.dto.SalespersonDTO;
import com.example.kohbergbackend.model.Salesperson;
import com.example.kohbergbackend.repository.SalespersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalespersonService {

    private SalespersonRepository salespersonRepository;
    private SalespersonConverter salespersonConverter;

    @Autowired
    public SalespersonService(SalespersonRepository salespersonRepository, SalespersonConverter salespersonConverter) {
        this.salespersonRepository = salespersonRepository;
        this.salespersonConverter = salespersonConverter;
    }


    public SalespersonDTO createSalesperson(SalespersonDTO salespersonDTO) {
        Salesperson salesperson = salespersonConverter.toEntity(salespersonDTO);
        Salesperson savedSalesperson = salespersonRepository.save(salesperson);
        return salespersonConverter.toDTO(savedSalesperson);
    }

    public List<SalespersonDTO> getAllSalespersons() {
        List<Salesperson> salespersons = salespersonRepository.findAll();
        return salespersonConverter.toDTOList(salespersons);
    }


    public Optional<SalespersonDTO> getSalespersonById(int salespersonId) {
        Optional<Salesperson> salespersonOptional = salespersonRepository.findById(salespersonId);
        return salespersonOptional.map(salespersonConverter::toDTO);
    }

    public SalespersonDTO updateSalesperson(int salespersonId, SalespersonDTO updatedSalespersonDTO) {
        if (salespersonRepository.existsById(salespersonId)) {
            Salesperson updatedSalesperson = salespersonConverter.toEntity(updatedSalespersonDTO);
            updatedSalesperson.setSalespersonID(salespersonId);
            Salesperson savedSalesperson = salespersonRepository.save(updatedSalesperson);
            return salespersonConverter.toDTO(savedSalesperson);
        }
        return null;
    }

    public void deleteSalesperson(int salespersonId) {
        salespersonRepository.deleteById(salespersonId);
    }

}

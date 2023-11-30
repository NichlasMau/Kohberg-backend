package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Salesperson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalespersonConverter {
    public Salesperson toEntity(SalespersonDTO salespersonDTO){
        return new Salesperson(
                salespersonDTO.id(),
                salespersonDTO.name(),
                salespersonDTO.birthday(),
                salespersonDTO.customers(),
                salespersonDTO.email()
        );
    }

    public SalespersonDTO toDTO(Salesperson salesperson){
        return new SalespersonDTO(
                salesperson.getSalespersonID(),
                salesperson.getName(),
                salesperson.getBirthday(),
                salesperson.getCustomers(),
                salesperson.getEmail()
        );
    }

    public List<SalespersonDTO> toDTOList(List<Salesperson> salespersons) {
        return salespersons.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

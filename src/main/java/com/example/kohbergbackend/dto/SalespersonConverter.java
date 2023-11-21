package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Salesperson;
import org.springframework.stereotype.Component;

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
                salesperson.getId(),
                salesperson.getName(),
                salesperson.getBirthday(),
                salesperson.getCustomers(),
                salesperson.getEmail()
        );
    }
}

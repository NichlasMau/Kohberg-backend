package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Salesperson;
import org.springframework.stereotype.Component;

@Component
public class SalespersonConverter {

    public Salesperson toEntity(SalespersonDTO salespersonDTO) {
        Salesperson salesperson = new Salesperson(
                salespersonDTO.userID(),
                salespersonDTO.name(),
                salespersonDTO.username(),
                "salesperson", // Du kan håndtere rollen baseret på DTO, hvis det er nødvendigt
                salespersonDTO.birthday(),
                salespersonDTO.customers(),
                salespersonDTO.email()
        );
        // Andre konverteringer, hvis nødvendigt
        return salesperson;
    }

    public SalespersonDTO toDTO(Salesperson salesperson) {
        SalespersonDTO salespersonDTO = new SalespersonDTO(
                salesperson.getUserID(),
                salesperson.getName(),
                salesperson.getUsername(),
                salesperson.getRole(),
                salesperson.getBirthday(),
                salesperson.getEmail(),
                salesperson.getCreationYear(),
                salesperson.getCustomers()
        );
        // Andre konverteringer, hvis nødvendigt
        return salespersonDTO;
    }

}

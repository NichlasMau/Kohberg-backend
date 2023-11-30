package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.Leader;
import com.example.kohbergbackend.model.Salesperson;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class LeaderConverter {

    public Leader toEntity(LeaderDTO leaderDTO) {
        return new Leader(
                leaderDTO.leaderID(),
                leaderDTO.hireDate(),
                leaderDTO.username(),
                leaderDTO.customers(),
                leaderDTO.salespersons()
        );
    }

    public LeaderDTO toDTO(Leader leader) {
        return new LeaderDTO(
                leader.getLeaderID(),
                leader.getHireDate(),
                leader.getUsername(),
                leader.getCustomers(),
                leader.getSalespersons()
        );
    }
}

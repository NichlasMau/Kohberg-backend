package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Leader;
import org.springframework.stereotype.Component;

@Component
public class LeaderConverter {
    public Leader toEntity(LeaderDTO leaderDTO) {
        return new Leader(
                leaderDTO.leaderID(),
                leaderDTO.name(),
                leaderDTO.role(),
                leaderDTO.birthday(),
                leaderDTO.email(),
                leaderDTO.position(),
                leaderDTO.hireDate()
        );
    }

    public LeaderDTO toDTO(Leader leader) {
        return new LeaderDTO(
                leader.getLeaderID(),
                leader.getUsername(),
                leader.getRole(),
                leader.getBirthday(),
                leader.getEmail(),
                leader.getPosition(),
                leader.getHireDate()
        );
    }
}

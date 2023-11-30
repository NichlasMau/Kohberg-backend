package com.example.kohbergbackend.service;

import com.example.kohbergbackend.exception.NotFoundException;
import com.example.kohbergbackend.model.Leader;
import com.example.kohbergbackend.repository.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaderService {
    private LeaderRepository leaderRepository;

    @Autowired
    public LeaderService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public Leader createLeader(Leader leader) {
        return leaderRepository.save(leader);
    }

    public List<Leader> getAllLeaders() {
        return leaderRepository.findAll();
    }

    public Optional<Leader> getLeaderById(int leaderId) {
        return leaderRepository.findById(leaderId)
                .map(Optional::of)
                .orElseThrow(() -> new NotFoundException("Leader not found with id: " + leaderId));
    }

    public Leader updateLeader(int leaderId, Leader leader) {
        return leaderRepository.save(leader);
    }

    public Leader deleteLeader(int leaderId) {
        Optional<Leader> leaderOptional = leaderRepository.findById(leaderId);
        if (leaderOptional.isPresent()) {
            leaderRepository.deleteById(leaderId);
            return leaderOptional.get();
        } else {
            throw new NotFoundException("Leader not found with id: " + leaderId);
        }
    }
}

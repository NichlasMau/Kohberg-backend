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

    public Leader updateLeader(int leaderId, Leader updatedLeader) {
        Leader existingLeader = leaderRepository.findById(leaderId)
                .orElseThrow(() -> new NotFoundException("Leader not found with ID: " + leaderId));

        // Update the attributes of the existing leader with the values from the updated leader
        existingLeader.setHireDate(updatedLeader.getHireDate());
        existingLeader.setUsername(updatedLeader.getUsername());
        existingLeader.setName(updatedLeader.getName());
        existingLeader.setPassword(updatedLeader.getPassword());
        existingLeader.setRole(updatedLeader.getRole());
        existingLeader.setBirthday(updatedLeader.getBirthday());
        existingLeader.setEmail(updatedLeader.getEmail());
        existingLeader.setCreationYear(updatedLeader.getCreationYear());

        // Save the updated leader in the repository
        return leaderRepository.save(existingLeader);
    }

    public void deleteLeader(int leaderId) {
        Optional<Leader> leaderOptional = leaderRepository.findById(leaderId);

        if (leaderOptional.isPresent()) {
            leaderRepository.deleteById(leaderId);
        } else {
            throw new NotFoundException("Leader not found with id: " + leaderId);
        }
    }

}

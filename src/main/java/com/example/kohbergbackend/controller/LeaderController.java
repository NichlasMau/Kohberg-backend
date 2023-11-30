package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.dto.CustomerDTO;
import com.example.kohbergbackend.dto.LeaderConverter;
import com.example.kohbergbackend.model.Leader;
import com.example.kohbergbackend.service.LeaderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/leader")
public class LeaderController {
    private LeaderService leaderService;

    @Autowired
    public LeaderController(LeaderService leaderService) {
        this.leaderService = leaderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Leader>> getAllLeaders() {
        List<Leader> leaders = leaderService.getAllLeaders();
        return new ResponseEntity<>(leaders, HttpStatus.OK);
    }

    @GetMapping("{leaderId}")
    public ResponseEntity<Leader> getLeaderById(int leaderId) {
        Optional<Leader> leader = leaderService.getLeaderById(leaderId);
        return leader.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{leaderId}")
    public ResponseEntity<Leader> updateLeader(@PathVariable int leaderId, @RequestBody Leader updatedLeaderData) {
        Leader updatedLeader = leaderService.updateLeader(leaderId, updatedLeaderData);
        return updatedLeader != null ?
                new ResponseEntity<>(updatedLeader, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{leaderId}")
    public ResponseEntity<Void> deleteLeader(@PathVariable int leaderId) {
        leaderService.deleteLeader(leaderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

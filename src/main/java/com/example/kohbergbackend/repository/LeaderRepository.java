package com.example.kohbergbackend.repository;

import com.example.kohbergbackend.model.Leader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderRepository extends JpaRepository<Leader, Integer> {
    Leader findByEmail(String email);

}

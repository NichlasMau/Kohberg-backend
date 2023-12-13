package com.example.kohbergbackend.repository;

import com.example.kohbergbackend.model.Leader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaderRepository extends JpaRepository<Leader, Integer> {
    Leader findByEmail(String email);
    List<Leader> findAllByEmail(String email);


}

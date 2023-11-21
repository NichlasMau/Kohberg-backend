package com.example.kohbergbackend.repository;

import com.example.kohbergbackend.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<Costumer, Integer> {
}

package com.example.kohbergbackend.repository;

import com.example.kohbergbackend.model.Salesperson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalespersonRepository extends JpaRepository<Salesperson, Integer> {
}

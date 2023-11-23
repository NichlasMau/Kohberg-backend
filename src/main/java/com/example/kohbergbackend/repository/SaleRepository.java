package com.example.kohbergbackend.repository;

import com.example.kohbergbackend.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}

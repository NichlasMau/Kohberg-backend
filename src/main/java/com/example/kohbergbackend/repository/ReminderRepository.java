package com.example.kohbergbackend.repository;

import com.example.kohbergbackend.model.Reminder;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Integer> {
}

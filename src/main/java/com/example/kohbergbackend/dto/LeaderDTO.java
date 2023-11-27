package com.example.kohbergbackend.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

public record LeaderDTO(int leaderID,String name, String role, LocalDate birthday,
                        String email, String position, LocalDate hireDate) {
}

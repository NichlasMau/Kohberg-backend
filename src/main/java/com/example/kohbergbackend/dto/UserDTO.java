package com.example.kohbergbackend.dto;

import java.sql.Date;
import java.time.LocalDate;

public record UserDTO(int userID, String username, String role, LocalDate birthday, String email){
}

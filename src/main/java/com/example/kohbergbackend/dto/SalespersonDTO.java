package com.example.kohbergbackend.dto;

import java.time.LocalDate;
import java.util.Date;

public record SalespersonDTO(int id, String name, LocalDate birthday, int customers, String email) {


}

package com.example.kohbergbackend.dto;

import java.time.LocalDate;

public record SaleDTO(int saleID, int customerID, LocalDate saleDate, String product) {


}

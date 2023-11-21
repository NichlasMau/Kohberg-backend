package com.example.kohbergbackend.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Sale {
    private int saleID;
    private int customerID;
    private LocalDate saleDate;
    private String orderValue;

    public Sale(int saleID, int customerID, LocalDate saleDate, String orderValue) {
        this.saleID = saleID;
        this.customerID = customerID;
        this.saleDate = saleDate;
        this.orderValue = orderValue;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public void displaySaleInfo() {
        System.out.println("Sale ID: " + saleID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Sale Date: " + saleDate);
        System.out.println("Order Value: " + orderValue);
    }
}

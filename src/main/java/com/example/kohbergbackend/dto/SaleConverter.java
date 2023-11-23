package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Sale;

public class SaleConverter {

    public Sale toEntity(SaleDTO saleDTO){
        return new Sale(
                saleDTO.saleID(),
                saleDTO.customerID(),
                saleDTO.saleDate(),
                saleDTO.product()
        );
    }

    public SaleDTO toDTO(Sale sale){
        return new SaleDTO(
                sale.getSaleID(),
                sale.getCustomerID(),
                sale.getSaleDate(),
                sale.getProduct()
        );
    }
}

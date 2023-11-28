package com.example.kohbergbackend.controller;


import com.example.kohbergbackend.model.Sale;
import com.example.kohbergbackend.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/sale")
public class SaleController {

    private SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale createdSale = saleService.createSale(sale);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<Sale> getCostumerById(@PathVariable int saleId) {
        Optional<Sale> sale = saleService.getSaleById(saleId);
        return sale.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{saleId}")
    public ResponseEntity<Sale> updateSale(@PathVariable int saleId, @RequestBody Sale updatedSaleData) {
        Sale updatedSale = saleService.updateSale(saleId, updatedSaleData);
        return updatedSale != null ?
                new ResponseEntity<>(updatedSale, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{saleId}")
    public ResponseEntity<Void> deleteCostumer(@PathVariable int saleId) {
        saleService.deleteSale(saleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

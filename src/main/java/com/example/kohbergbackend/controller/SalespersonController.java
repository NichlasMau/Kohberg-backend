package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.dto.SalespersonDTO;
import com.example.kohbergbackend.service.SalespersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/salesperson")
public class SalespersonController {

    private SalespersonService salespersonService;

    public SalespersonController(SalespersonService salespersonService) {
        this.salespersonService = salespersonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SalespersonDTO>> getAllSalespersons() {
        List<SalespersonDTO> salespersons = salespersonService.getAllSalespersons();
        return new ResponseEntity<>(salespersons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalespersonDTO> getSalespersonById(@PathVariable int id) {
        Optional<SalespersonDTO> salesperson = salespersonService.getSalespersonById(id);
        return salesperson.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<SalespersonDTO> createSalesperson(@RequestBody SalespersonDTO salesperson) {
        SalespersonDTO createdSalesperson = salespersonService.createSalesperson(salesperson);
        return new ResponseEntity<>(createdSalesperson, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SalespersonDTO> updateSalesperson(@PathVariable int id, @RequestBody SalespersonDTO updatedSalespersonData) {
        SalespersonDTO updatedSalesperson = salespersonService.updateSalesperson(id, updatedSalespersonData);
        return updatedSalesperson != null ?
                new ResponseEntity<>(updatedSalesperson, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSalesperson(@PathVariable int id) {
        salespersonService.deleteSalesperson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

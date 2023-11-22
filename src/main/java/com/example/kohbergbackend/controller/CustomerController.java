package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.model.Costumer;
import com.example.kohbergbackend.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/costumer")
public class CustomerController {

    private CostumerService costumerService;

    @Autowired
    public CustomerController(CostumerService costumerService){
        this.costumerService = costumerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Costumer> createCostumer(@RequestBody Costumer costumer) {
        Costumer createdCostumer = costumerService.createCostumer(costumer);
        return new ResponseEntity<>(createdCostumer, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Costumer>> getAllCostumers() {
        List<Costumer> costumers = costumerService.getAllCostumers();
        return new ResponseEntity<>(costumers, HttpStatus.OK);
    }

    @GetMapping("/{costumerId}")
    public ResponseEntity<Costumer> getCostumerById(@PathVariable int costumerId) {
        Optional<Costumer> costumer = costumerService.getCostumerById(costumerId);
        return costumer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{costumerId}")
    public ResponseEntity<Costumer> updateCostumer(@PathVariable int costumerId, @RequestBody Costumer updatedCostumerData) {
        Costumer updatedCostumer = costumerService.updateCostumer(costumerId, updatedCostumerData);
        return updatedCostumer != null ?
                new ResponseEntity<>(updatedCostumer, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{costumerId}")
    public ResponseEntity<Void> deleteCostumer(@PathVariable int costumerId) {
        costumerService.deleteCostumer(costumerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
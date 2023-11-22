package com.example.kohbergbackend.controller;
import com.example.kohbergbackend.dto.SalespersonDTO;
import com.example.kohbergbackend.service.SalespersonService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class SalespersonController {

    private SalespersonService salespersonService;

    @GetMapping ("/salesperson/{id}")
    public ResponseEntity<SalespersonDTO> getSalespersonById(@PathVariable("id") int id){
        SalespersonDTO salespersonDTO = salespersonService.getSalespersonById(id);
        return ResponseEntity.ok(salespersonDTO);
    }

     @GetMapping("/salespersons")
    public ResponseEntity<List<SalespersonDTO>> getAllSalespersons(){
        List<SalespersonDTO> salespersonDTOList = salespersonService.getAllSalesperson();
        return new ResponseEntity<>(salespersonDTOList, HttpStatus.OK);
     }

    @PutMapping("/salesperson/{id}")
    public ResponseEntity<SalespersonDTO> updateSalesperson(@PathVariable("id")int id, @RequestBody SalespersonDTO salespersonDTO){
        SalespersonDTO updatedEmployeeDTO = salespersonService.updateSalesperson(id, salespersonDTO);
        return ResponseEntity.ok(updatedEmployeeDTO);
    }

    @PostMapping("/salesperson")
    public ResponseEntity<SalespersonDTO> createSalesperson(@RequestBody SalespersonDTO salespersonDTO){
        SalespersonDTO createdSalesperson = salespersonService.createSalesperson(salespersonDTO);
        return new ResponseEntity<>(createdSalesperson, HttpStatus.CREATED);
    }

    @DeleteMapping("/salesperson/{id}")
    public ResponseEntity<Void> deleteSalesperson(@PathVariable("id") int id) {
        salespersonService.deleteSalesperson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
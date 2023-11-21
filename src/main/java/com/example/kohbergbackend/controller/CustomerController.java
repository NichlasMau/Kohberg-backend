package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/costumer")
public class CustomerController {

    private CostumerService costumerService;

    @Autowired
    public CustomerController(CostumerService costumerService){
        this.costumerService = costumerService;
    }


}

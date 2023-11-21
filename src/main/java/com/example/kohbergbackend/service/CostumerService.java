package com.example.kohbergbackend.service;

import com.example.kohbergbackend.model.Costumer;
import com.example.kohbergbackend.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerService {

    private CostumerRepository costumerRepository;

    @Autowired
    public CostumerService(CostumerRepository costumerRepository){
        this.costumerRepository = costumerRepository;
    }

    public Costumer createCostumer(Costumer costumer) {
        return costumerRepository.save(costumer);
    }

    public List<Costumer> getAllCostumers() {
        return costumerRepository.findAll();
    }

    public Optional<Costumer> getCostumerById(int costumerId) {
        return costumerRepository.findById(costumerId);
    }

    public Costumer updateCostumer(int costumerId) {
        if (costumerRepository.existsById(costumerId)) {
            Costumer updatedCostumer = costumerRepository.setId(costumerId);
            return costumerRepository.save(updatedCostumer);
        }
        return null;
    }

    public void deleteCostumer(int costumerId) {
        costumerRepository.deleteById(costumerId);
    }

}

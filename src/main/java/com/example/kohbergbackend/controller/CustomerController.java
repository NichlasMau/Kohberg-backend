package com.example.kohbergbackend.controller;

import com.example.kohbergbackend.model.Costumer;
import com.example.kohbergbackend.model.User;
import com.example.kohbergbackend.service.UserService;
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

    private UserService userService;

    @Autowired
    public CustomerController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createCostumer(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllCostumers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getCostumerById(@PathVariable int userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateCostumer(@PathVariable int userId, @RequestBody User updatedUserData) {
        User updatedCostumer = userService.updateUser(userId, updatedUserData);
        return updatedCostumer != null ?
                new ResponseEntity<>(updatedCostumer, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{costumerId}")
    public ResponseEntity<Void> deleteCostumer(@PathVariable int costumerId) {
        userService.deleteUser(costumerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
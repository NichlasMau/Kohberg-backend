package com.example.kohbergbackend.repository;

import com.example.kohbergbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}

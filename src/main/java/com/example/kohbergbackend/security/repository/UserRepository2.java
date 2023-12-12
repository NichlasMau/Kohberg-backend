package com.example.kohbergbackend.security.repository;

import com.example.kohbergbackend.security.model.User2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository2 extends JpaRepository<User2,Long> {
    List<User2> findByUsername(String name);
    //List<User> findUserByPasswordContains(String passwordPart);
}

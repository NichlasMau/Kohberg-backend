package com.example.kohbergbackend.security.repository;

import com.example.kohbergbackend.security.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByUsername(String name);
    //List<User> findUserByPasswordContains(String passwordPart);
}

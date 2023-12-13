package com.example.kohbergbackend.security.service;

import com.example.kohbergbackend.security.config.SecurityConfiguration;
import com.example.kohbergbackend.security.model.Employee;
import com.example.kohbergbackend.security.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class EmployeeService implements IUserService{

    private EmployeeRepository userRepository;
    @Override
    public Set<Employee> findAll() {
        Set<Employee> set = new HashSet<>();
        userRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Employee save(Employee user) {
//        if(user.getPassword() == null) {
            PasswordEncoder pw = SecurityConfiguration.passwordEncoder();
            user.setPassword(pw.encode(user.getPassword()));
//        }
        return userRepository.save(user);
    }

    @Override
    public void delete(Employee object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        return userRepository.findById(aLong);
    }

    @Override
    public List<Employee> findByName(String name) {
        System.out.println("Userservice called findByName with argument: " + name);
        return userRepository.findByUsername(name);
    }
}

package com.example.kohbergbackend.security.service;

import com.example.kohbergbackend.security.config.SecurityConfiguration;
import com.example.kohbergbackend.security.model.User2;
import com.example.kohbergbackend.security.repository.UserRepository2;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService2 implements IUserService{

    private UserRepository2 userRepository;
    @Override
    public Set<User2> findAll() {
        Set<User2> set = new HashSet<>();
        userRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public User2 save(User2 user) {
//        if(user.getPassword() == null) {
            PasswordEncoder pw = SecurityConfiguration.passwordEncoder();
            user.setPassword(pw.encode(user.getPassword()));
//        }
        return userRepository.save(user);
    }

    @Override
    public void delete(User2 object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<User2> findById(Long aLong) {
        return userRepository.findById(aLong);
    }

    @Override
    public List<User2> findByName(String name) {
        System.out.println("Userservice called findByName with argument: " + name);
        return userRepository.findByUsername(name);
    }
}

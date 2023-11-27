package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Salesperson;
import com.example.kohbergbackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toEntity(UserDTO userDTO){
        return new User(
                userDTO.userID(),
                userDTO.username(),
                userDTO.role(),
                userDTO.birthday(),
                userDTO.email(),
                userDTO.creationYear()
        );
    }

    public UserDTO toDTO(User user){
        return new UserDTO(
                user.getUserID(),
                user.getUsername(),
                user.getRole(),
                user.getBirthday(),
                user.getEmail(),
                user.getCreationYear()
        );
    }
}

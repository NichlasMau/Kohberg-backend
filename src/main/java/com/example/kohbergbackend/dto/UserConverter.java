package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getUserID(),
                user.getName(), user.getRole(),
                user.getBirthday(), user.getEmail(),
                user.getCreationYear());
    }

    public User toEntity(UserDTO userDTO) {
        return new User( userDTO.userID(), userDTO.name(),
                userDTO.role(), userDTO.birthday(), userDTO.email(),
                userDTO.creationYear());
    }
}
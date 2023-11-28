package com.example.kohbergbackend.service;

import com.example.kohbergbackend.exception.NotFoundException;
import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.Leader;
import com.example.kohbergbackend.model.Salesperson;
import com.example.kohbergbackend.model.User;
import com.example.kohbergbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing User entities.
 */
@Service
public class UserService {

    private UserRepository userRepository;


    /**
     * Constructor for UserService.
     *
     * @param userRepository The repository for managing User entities.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user.
     *
     * @param user The User entity to be created.
     * @return The created User entity.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all User entities.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by its unique identifier.
     *
     * @param userId The unique identifier of the User to retrieve.
     * @return The retrieved User entity.
     * @throws NotFoundException if the user is not found.
     */
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId)
                .map(Optional::of)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    /**
     * Updates an existing user.
     *
     * @param userId          The unique identifier of the User to update.
     * @param updatedUserData The updated User data.
     * @return The updated User entity.
     * @throws NotFoundException if the user is not found.
     */
    public User updateUser(int userId, User updatedUserData) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    // Opdater de grundlæggende attributter for User
                    existingUser.setName(updatedUserData.getName());
                    existingUser.setBirthday(updatedUserData.getBirthday());

                    // Opdater eventuelle yderligere attributter for User
                    existingUser.setEmail(updatedUserData.getEmail());
                    existingUser.setCreationYear(updatedUserData.getCreationYear());

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }


    /**
     * Deletes a user by its unique identifier.
     *
     * @param userId The unique identifier of the User to delete.
     * @throws NotFoundException if the user is not found.
     */
    public void deleteUser(int userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        userRepository.deleteById(userId);
    }
}
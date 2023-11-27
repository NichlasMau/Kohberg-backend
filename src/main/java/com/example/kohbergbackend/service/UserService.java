package com.example.kohbergbackend.service;

import com.example.kohbergbackend.model.Costumer;
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
     * @return An Optional containing the retrieved User, or an empty Optional if not found.
     */
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    /**
     * Updates an existing user.
     *
     * @param userId          The unique identifier of the User to update.
     * @param updatedUserData The updated User data.
     * @return The updated User entity, or null if the User was not found.
     */
    public User updateUser(int userId, User updatedUserData) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    // Update user attributes based on the type of user (leader, customer, salesperson)
                    // You might want to handle this in a more generic way based on your user hierarchy
                    existingUser.setUsername(updatedUserData.getUsername());
                    existingUser.setBirthday(updatedUserData.getBirthday());

                    // Additional attributes for specific user types
                    if (existingUser instanceof Leader) {
                        ((Leader) existingUser).setPosition(((Leader) updatedUserData).getPosition());
                        ((Leader) existingUser).setHireDate(((Leader) updatedUserData).getHireDate());
                    } else if (existingUser instanceof Costumer) {
                        ((Costumer) existingUser).setCreationYear(((Costumer) updatedUserData).getCreationYear());
                    } else if (existingUser instanceof Salesperson) {
                        ((Salesperson) existingUser).setCustomer(((Salesperson) updatedUserData).getCustomer());
                    }

                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

    /**
     * Deletes a user by its unique identifier.
     *
     * @param userId The unique identifier of the User to delete.
     */
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
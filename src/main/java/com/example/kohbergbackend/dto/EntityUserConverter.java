package com.example.kohbergbackend.dto;

import com.example.kohbergbackend.model.Customer;
import com.example.kohbergbackend.model.Leader;
import com.example.kohbergbackend.model.Salesperson;
import com.example.kohbergbackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class EntityUserConverter {

    public UserDTO toDTO(User user) {
        if (user instanceof Leader) {
            return toLeaderDTO((Leader) user);
        } else if (user instanceof Salesperson) {
            return toSalespersonDTO((Salesperson) user);
        } else if (user instanceof Customer) {
            return toCustomerDTO((Customer) user);
        }

        // Default case: User
        return new UserDTO(
                user.getUserID(),
                user.getName(),
                "",
                user.getRole(),
                user.getBirthday(),
                user.getEmail(),
                user.getCreationYear(),
                null,  // Default value for hireDate
                null,  // Default value for customers
                null  // Default value for salespersons
        );
    }

    public UserDTO toLeaderDTO(Leader leader) {
        return new UserDTO(
                leader.getLeaderID(),
                leader.getName(),
                leader.getUsername(),
                "Leader",
                leader.getBirthday(),
                leader.getEmail(),
                leader.getCreationYear(),
                leader.getHireDate(),
                leader.getCustomers(),
                leader.getSalespersons()
        );
    }

    public UserDTO toSalespersonDTO(Salesperson salesperson) {
        return new UserDTO(
                salesperson.getUserID(),
                salesperson.getName(),
                salesperson.getUsername(),
                "Salesperson",
                salesperson.getBirthday(),
                salesperson.getEmail(),
                salesperson.getCreationYear(),
                null,
                salesperson.getCustomers(),
                null
        );
    }

    public UserDTO toCustomerDTO(Customer customer) {
        return new UserDTO(
                customer.getCostumerID(),
                customer.getName(),
                "",
                "Customer",
                customer.getBirthday(),
                customer.getEmail(),
                customer.getCreationYear(),
                null,
                null,
                null
        );
    }

}
package com.example.kohbergbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * The {@code Salesperson} class represents a salesperson in the system.
 * Salespeople can have associated customers, a leader, and various personal information.
 *
 * <p>This class is an entity mapped to the database table representing salespersons.
 *
 * @author [Your Name]
 * @version 1.0
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Salesperson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salespersonID;

    private String username;
    private String name;
    private String password;
    private String role;
    private LocalDate birthday;
    private String email;
    private LocalDate creationYear;

    @OneToMany(mappedBy = "salesperson")
    private List<Customer> customers;

    @ManyToOne
    @JoinColumn(name = "leader_id") // sørg for, at dette navn matcher det, du har i Leader-klassen
    private Leader leader;

    public Salesperson(int id, String name, LocalDate birthday, List<Customer> customers, String email) {
        this.salespersonID = id;
        this.name = name;
        this.birthday = birthday;
        this.customers = customers;
        this.email = email;
    }

    /**
     * Gets the formatted reminder date based on the birthday.
     *
     * @return A formatted reminder date string (e.g., "dd.MM.yyyy").
     */
    @Transient
    public String getFormattedReminderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("da", "DK"));
        return getBirthday().format(formatter);
    }
}

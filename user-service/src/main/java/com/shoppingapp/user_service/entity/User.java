package com.shoppingapp.user_service.entity;

import com.shoppingapp.user_service.enums.Role;
import com.shoppingapp.user_service.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
        }
)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // will generate UUID if using UUID type
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;



    // | Field       | Type          | Notes                        |
    //| ----------- | ------------- | ---------------------------- |
    //| id          | UUID / BIGINT | Primary key                  |
    //| username    | VARCHAR(50)   | Unique                       |
    //| email       | VARCHAR(100)  | Unique                       |
    //| password    | VARCHAR(255)  | Hashed                       |
    //| role        | ENUM          | \[CUSTOMER, ADMIN]           |
    //| phone       | VARCHAR(15)   | Optional                     |
    //| status      | ENUM          | \[ACTIVE, INACTIVE, DELETED] |
    //| created\_at | TIMESTAMP     | Default NOW()                |
    //| updated\_at | TIMESTAMP     | Auto-updated                 |
}

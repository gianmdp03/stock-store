package com.stockstore.stockstore.security.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    private String phoneNumber;

    public User(String email, String username, String password, String name, String lastname, String phoneNumber) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }
}

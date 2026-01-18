package com.stockstore.stockstore.online.model;

import com.stockstore.stockstore.security.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    private String apartment;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String province;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private boolean enabled = true;

    public Address(String name, String street, String number, String apartment, String zipCode, String city, String province, User user, boolean enabled) {
        this.name = name;
        this.street = street;
        this.number = number;
        this.apartment = apartment;
        this.zipCode = zipCode;
        this.city = city;
        this.province = province;
        this.user = user;
        this.enabled = enabled;
    }
}

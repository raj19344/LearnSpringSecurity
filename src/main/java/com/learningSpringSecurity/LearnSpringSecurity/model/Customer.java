package com.learningSpringSecurity.LearnSpringSecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Authority authority;

    public Customer(){}

    public Customer(Long id, String email, String password, Authority authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    public Customer(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    public Customer(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}

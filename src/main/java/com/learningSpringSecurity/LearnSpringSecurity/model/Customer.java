package com.learningSpringSecurity.LearnSpringSecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
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

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

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

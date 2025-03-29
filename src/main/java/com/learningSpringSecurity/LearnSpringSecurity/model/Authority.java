package com.learningSpringSecurity.LearnSpringSecurity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Getter @Setter
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Authority(){}


    public Authority(String name) {
        this.name = name;
    }

    public Authority(Long id) {
        this.id = id;
    }

}

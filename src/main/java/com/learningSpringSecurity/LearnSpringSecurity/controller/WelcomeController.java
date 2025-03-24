package com.learningSpringSecurity.LearnSpringSecurity.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello Happy@example.com";
    }

    @PutMapping("/update")
    public String update(){
        return "update controller";
    }

    @PostMapping("/create")
    public String createUser(){
        return "create user";
    }

    @DeleteMapping("/delete")
    public String deleteUser(){
        return "delete the user";
    }


}

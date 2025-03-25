package com.learningSpringSecurity.LearnSpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String getWelcome(){
        return "Welcome page for all";
    }
}

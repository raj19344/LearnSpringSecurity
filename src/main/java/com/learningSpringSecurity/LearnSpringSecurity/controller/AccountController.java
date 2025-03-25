package com.learningSpringSecurity.LearnSpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/accounts")
    public String getAccountPage(){
        return "Welcome user to the account page";
    }
}

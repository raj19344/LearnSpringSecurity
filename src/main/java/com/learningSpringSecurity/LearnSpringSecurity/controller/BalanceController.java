package com.learningSpringSecurity.LearnSpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/balances")
    public String getBalance(){
        return "Display balance page";
    }
}

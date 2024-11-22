package com.spring.security.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String sayWelcome(){
        return "Here are the balance details of my DB.";
    }


}

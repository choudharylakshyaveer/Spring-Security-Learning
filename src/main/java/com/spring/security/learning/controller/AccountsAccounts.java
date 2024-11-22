package com.spring.security.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsAccounts {

    @GetMapping("/myAccount")
    public String sayWelcome(){
        return "Here are the account details of my DB.";
    }


}

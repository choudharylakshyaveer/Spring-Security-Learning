package com.spring.security.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/myCards")
    public String sayWelcome(){
        return "Here are the card details of my DB.";
    }


}

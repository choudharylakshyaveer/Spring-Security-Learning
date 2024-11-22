package com.spring.security.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public String sayWelcome(){
        return "Enquery details are saved to DB.";
    }


}

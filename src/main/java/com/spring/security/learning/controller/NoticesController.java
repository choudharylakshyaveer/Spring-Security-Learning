package com.spring.security.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping("/notices")
    public String sayWelcome(){
        return "Here are the notice details from the DB.";
    }


}

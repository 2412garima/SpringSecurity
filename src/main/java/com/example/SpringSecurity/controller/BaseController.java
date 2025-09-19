package com.example.SpringSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        return "Welcome to the Home Page!" +request.getSession().getId();
    }
}

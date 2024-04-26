package com.khun.movievault.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movievault/v1")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}

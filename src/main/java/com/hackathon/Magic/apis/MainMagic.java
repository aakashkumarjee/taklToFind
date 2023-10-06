package com.hackathon.Magic.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainMagic {


        // Define a GET endpoint
        @GetMapping("/")
        public String sayHello() {
            return "Hello, World!";
        }

        @PostMapping("/magic-api/generate-keywords")
        public String generateKeywords() {
            return "Hello, World!";
        }

}

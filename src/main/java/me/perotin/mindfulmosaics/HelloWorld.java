package me.perotin.mindfulmosaics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWorld {


    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}

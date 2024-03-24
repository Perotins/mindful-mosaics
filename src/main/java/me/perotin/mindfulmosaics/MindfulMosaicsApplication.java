package me.perotin.mindfulmosaics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MindfulMosaicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindfulMosaicsApplication.class, args);
    }




    protected void configure(HttpSecurity http) throws Exception {
        http
                // ... other configuration ...
                .csrf().disable();
    }

}

package me.perotin.mindfulmosaics.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsGlobalConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // or more specific path patterns
                        .allowedOrigins("http://localhost:3000") // the URL your front-end is served from
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD") // etc.
                        .allowedHeaders("Content-Type", "Authorization"); // etc.
            }
        };
    }
}

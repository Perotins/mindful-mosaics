package me.perotin.mindfulmosaics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class MindfulMosaicsApplication {

    @Autowired
    private UserDetailsService userDetailsService;
    public static void main(String[] args) {


        SpringApplication.run(MindfulMosaicsApplication.class, args);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "X-CSRF-TOKEN"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        System.out.println("Setting up CORS with allowCredentials=true2");


        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors().and().csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/register").permitAll() // Allow public access to the register endpoint
                        .anyRequest().authenticated() // All other requests need to be authenticated
                )
                .httpBasic(Customizer.withDefaults());

        // Configure AuthenticationManager with UserDetailsService and PasswordEncoder
        http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

//        http
//                // Apply CORS configuration
//                .cors().configurationSource(corsConfigurationSource())
//                .and()
//                // Disable CSRF protection
//                .csrf().disable()
//                // Configure authorization requests
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/register").permitAll() // Allow registration without authentication
//                        .anyRequest().authenticated()) // All other requests need to be authenticated
//                // Use httpBasic authentication
//                .httpBasic(withDefaults());
        System.out.println("Configuring HttpSecurity");
//        http
//                .cors().and().csrf().disable()
//
////                .csrf(csrf -> csrf
////                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Use a cookie for CSRF
////                )
//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//
//                .httpBasic(Customizer.withDefaults());



        // Configure AuthenticationManager with UserDetailsService and PasswordEncoder
//        http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(new BCryptPasswordEncoder());

        // rest of your configuration...
        ;

        return http.build();
    }


}

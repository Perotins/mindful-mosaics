package me.perotin.mindfulmosaics.controllers;

import me.perotin.mindfulmosaics.models.User;
import me.perotin.mindfulmosaics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationCtl {

    @Autowired
    private UserService userService;

    @CrossOrigin("http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST, path ="/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            System.out.println("11");
            // Assuming you have a method in your UserService to handle the registration
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            // Handle exceptions such as a user already existing with that email or username
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

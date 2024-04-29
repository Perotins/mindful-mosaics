package me.perotin.mindfulmosaics.services;

import me.perotin.mindfulmosaics.models.User;
import me.perotin.mindfulmosaics.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setLastLoginDate(new Date());
        user.setCreated_at(new Date());
        return userRepository.save(user);
    }
}

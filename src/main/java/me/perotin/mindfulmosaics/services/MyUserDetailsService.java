package me.perotin.mindfulmosaics.services;

import me.perotin.mindfulmosaics.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    // Inject your user repository here to load user data
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        me.perotin.mindfulmosaics.models.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Here we are just using the username and password. You might want to add roles, etc.
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER") // Assign default role as USER. You may want to fetch actual roles
                .build();
    }
}

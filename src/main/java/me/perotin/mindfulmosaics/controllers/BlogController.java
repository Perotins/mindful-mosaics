package me.perotin.mindfulmosaics.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import me.perotin.mindfulmosaics.models.Blog;
import me.perotin.mindfulmosaics.models.User;
import me.perotin.mindfulmosaics.repositories.BlogRepository;
import me.perotin.mindfulmosaics.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;


//    @PostMapping("/create-blog")
//    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
//        // Here you should set the current logged-in user to the blog
//        // For simplicity, we're skipping that step
//        Blog savedBlog = blogRepository.save(blog);
//        return ResponseEntity.ok(savedBlog);
//    }

    @PostMapping("/create-blog")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        // Retrieve the currently authenticated user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(principal.getClass().getName()); // Check the actual class of the principal
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            // Use the username to find the User entity
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

            // Set the user as the author of the blog
            System.out.println("User is: " + user.getUsername() + " with ID " + user.getId());
            blog.setUser(user);
        } else {
            // Handle the case where the principal is not an instance of UserDetails
            String username = principal.toString();
            System.out.println(username);
        }

        Blog savedBlog = blogRepository.save(blog);
        return ResponseEntity.ok(savedBlog);
    }

    // Additional methods for listing, updating, deleting blogs
}

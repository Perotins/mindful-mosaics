package me.perotin.mindfulmosaics.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import me.perotin.mindfulmosaics.models.Blog;
import me.perotin.mindfulmosaics.models.User;
import me.perotin.mindfulmosaics.repositories.BlogRepository;
import me.perotin.mindfulmosaics.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return ResponseEntity.ok(blogs);
    }
    @GetMapping("/{userId}/{title}")
    public ResponseEntity<Blog> getBlogByUsernameAndTitle(@PathVariable long userId, @PathVariable String title) {
        Optional<Blog> blog = blogRepository.findBlogByUserIdAndTitle(userId, title);
        return blog.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/user-blogs")
    public ResponseEntity<List<Blog>> getUserBlogs() {
        // Retrieve the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        System.out.println("Current username: " + currentUsername);
        // Find the user by username
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + currentUsername));

        // Get the blogs for the authenticated user
        List<Blog> userBlogs = blogRepository.findAllByUser(user);
        userBlogs.forEach(b -> System.out.println(b.getTitle()));
        return ResponseEntity.ok(userBlogs);
    }

    @PutMapping("/update-blog/{userId}/{title}")
    public ResponseEntity<?> updateBlog(@PathVariable long userId, @PathVariable String title, @RequestBody String content) {
        // Find the blog by user ID and title (consider adding additional checks for user authentication)
        Optional<Blog> blogOptional = blogRepository.findBlogByUserIdAndTitle(userId, title);

        if (blogOptional.isEmpty()) {
            return new ResponseEntity<>("Blog not found", HttpStatus.NOT_FOUND);
        }

        Blog blog = blogOptional.get();

        // Additional security checks can be added here to ensure the correct user is updating the blog

        // Update the content of the blog
        blog.setContent(content);

        // Save the updated blog
        blogRepository.save(blog);

        // Return the updated blog
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }




}

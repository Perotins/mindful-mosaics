package me.perotin.mindfulmosaics.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.persistence.EntityNotFoundException;
import me.perotin.mindfulmosaics.dto.BlogContentDTO;
import me.perotin.mindfulmosaics.models.Blog;
import me.perotin.mindfulmosaics.models.Like;
import me.perotin.mindfulmosaics.models.User;
import me.perotin.mindfulmosaics.repositories.BlogRepository;
import me.perotin.mindfulmosaics.repositories.LikeRepository;
import me.perotin.mindfulmosaics.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;


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
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

            System.out.println("User is: " + user.getUsername() + " with ID " + user.getId());
            blog.setUser(user);
        } else {
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        System.out.println("Current username: " + currentUsername);
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + currentUsername));

        List<Blog> userBlogs = blogRepository.findAllByUser(user);
        userBlogs.forEach(b -> System.out.println(b.getTitle()));
        return ResponseEntity.ok(userBlogs);
    }

    @PutMapping("/update-blog/{userId}/{title}")
    public ResponseEntity<?> updateBlog(@PathVariable long userId, @PathVariable String title, @RequestBody BlogContentDTO blogContentDTO) {
        Optional<Blog> blogOptional = blogRepository.findBlogByUserIdAndTitle(userId, title);

        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>("Blog not found", HttpStatus.NOT_FOUND);
        }

        Blog blog = blogOptional.get();

        blog.setContent(blogContentDTO.getContent());

        blogRepository.save(blog);

        return new ResponseEntity<>(blog, HttpStatus.OK);
    }


    @DeleteMapping("/{userId}/{title}")
    public ResponseEntity<?> deleteBlog(@PathVariable long userId, @PathVariable String title) {
        Optional<Blog> blog = blogRepository.findBlogByUserIdAndTitle(userId, title);
        if (blog.isPresent()) {
            blogRepository.delete(blog.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/blogs/{blogId}/like")
    public ResponseEntity<?> likeBlog(@PathVariable Long blogId, Principal principal) {
        // Find the user by username
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + principal.getName()));

        // Find the blog by blogId
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + blogId));

        // Create a new Like instance
        Like like = new Like();
        like.setUser(user);
        like.setBlog(blog);
        like.setLikedAt(new Date());

        // Save the like
        likeRepository.save(like);

        // Increment the likes count in the Blog entity
        blog.setLikes(blog.getLikes() + 1);
        blogRepository.save(blog);

        return ResponseEntity.ok().build();
    }







}

package me.perotin.mindfulmosaics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void likeBlog(Long blogId) {
        String sql = "UPDATE blog SET likes = likes + 1 WHERE id = ?";
        jdbcTemplate.update(sql, blogId); // uses prepared statement here
    }
}

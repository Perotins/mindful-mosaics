package me.perotin.mindfulmosaics.service;

import me.perotin.mindfulmosaics.models.Blog;
import me.perotin.mindfulmosaics.models.DeletionLog;
import me.perotin.mindfulmosaics.repositories.BlogRepository;
import me.perotin.mindfulmosaics.repositories.DeletionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BlogService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private DeletionLogRepository deletionLogRepository;

    public void likeBlog(Long blogId) {
        String sql = "UPDATE blog SET likes = likes + 1 WHERE id = ?";
        jdbcTemplate.update(sql, blogId); // uses prepared statement here
    }

    @Transactional
    public void deleteBlog(Long blogId, Long userId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        jdbcTemplate.update("DELETE FROM likes WHERE blog_id = ?", blogId);
        jdbcTemplate.update("DELETE FROM tags WHERE blog_id = ?", blogId);


        blogRepository.delete(blog);

        DeletionLog log = new DeletionLog();
        log.setBlogId(blogId);
        log.setDeletedByUserId(userId);
        log.setDeletedAt(LocalDateTime.now());
        deletionLogRepository.save(log);
    }
}

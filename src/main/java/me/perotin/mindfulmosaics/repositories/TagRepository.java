package me.perotin.mindfulmosaics.repositories;

import me.perotin.mindfulmosaics.models.Blog;
import me.perotin.mindfulmosaics.models.DeletionLog;
import me.perotin.mindfulmosaics.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByBlogAndName(Blog blog, String tag);
}

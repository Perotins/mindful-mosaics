package me.perotin.mindfulmosaics.repositories;

import me.perotin.mindfulmosaics.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}

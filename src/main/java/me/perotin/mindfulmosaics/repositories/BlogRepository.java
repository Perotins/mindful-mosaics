package me.perotin.mindfulmosaics.repositories;

import me.perotin.mindfulmosaics.models.Blog;
import me.perotin.mindfulmosaics.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findBlogByUserIdAndTitle(@Param("user_id") long user, @Param("title") String title);

    List<Blog> findAllByUser(User user);


}

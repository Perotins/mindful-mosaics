package me.perotin.mindfulmosaics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import me.perotin.mindfulmosaics.models.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // You can define custom query methods here if needed
}


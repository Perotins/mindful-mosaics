package me.perotin.mindfulmosaics.repositories;

import me.perotin.mindfulmosaics.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

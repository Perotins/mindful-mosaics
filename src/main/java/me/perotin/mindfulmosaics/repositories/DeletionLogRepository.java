package me.perotin.mindfulmosaics.repositories;

import me.perotin.mindfulmosaics.models.DeletionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletionLogRepository extends JpaRepository<DeletionLog, Long> {
}

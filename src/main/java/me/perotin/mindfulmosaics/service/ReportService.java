package me.perotin.mindfulmosaics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getBlogsCreatedWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT COUNT(*) FROM blog WHERE date_created BETWEEN ? AND ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, Integer.class);
    }

    public int getUsersRegisteredWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT COUNT(*) FROM users WHERE created_at BETWEEN ? AND ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, Integer.class);
    }

    public int getLikesWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT COUNT(*) FROM likes WHERE liked_at BETWEEN ? AND ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, Integer.class);
    }

    private LocalDateTime getStartTimeBasedOnTimeFrame(String timeFrame, LocalDateTime endTime) {
        switch (timeFrame) {
            case "1day":
                return endTime.minus(1, ChronoUnit.DAYS);
            case "1week":
                return endTime.minus(1, ChronoUnit.WEEKS);
            case "1month":
                return endTime.minus(1, ChronoUnit.MONTHS);
            default:
                throw new IllegalArgumentException("Invalid time frame specified");
        }
    }
}

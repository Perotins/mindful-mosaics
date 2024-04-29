package me.perotin.mindfulmosaics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> generateReport(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = calculateStartTime(timeFrame, endTime);

        String usersQuery = "SELECT COUNT(*) FROM users WHERE created_at BETWEEN ? AND ?";
        String blogsQuery = "SELECT COUNT(*) FROM blog WHERE date_created BETWEEN ? AND ?";
        String likesQuery = "SELECT SUM(likes) FROM blog WHERE date_created BETWEEN ? AND ?";

        int usersRegistered = jdbcTemplate.queryForObject(usersQuery, new Object[]{startTime, endTime}, Integer.class);
        int blogsCreated = jdbcTemplate.queryForObject(blogsQuery, new Object[]{startTime, endTime}, Integer.class);
        int totalLikes = jdbcTemplate.queryForObject(likesQuery, new Object[]{startTime, endTime}, Integer.class);

        // You'll need to implement getAverageBlogLength() and other methods for missing data
        int averageBlogLength = getAverageBlogLength(startTime, endTime);

        Map<String, Object> report = new HashMap<>();
        report.put("Time span", timeFrame);
        report.put("Users registered", usersRegistered);
        report.put("Blogs created", blogsCreated);
        report.put("Total likes", totalLikes);
        report.put("Average blog length", averageBlogLength);
        // Add more fields as per your actual schema and data

        return report;
    }

    private LocalDateTime calculateStartTime(String timeFrame, LocalDateTime endTime) {
        switch (timeFrame) {
            case "1 day":
                return endTime.minusDays(1);
            case "1 week":
                return endTime.minusWeeks(1);
            case "1 month":
                return endTime.minusMonths(1);
            default:
                throw new IllegalArgumentException("Invalid time frame");
        }
    }

    private int getAverageBlogLength(LocalDateTime startTime, LocalDateTime endTime) {
        // Example query to calculate average length of blogs
        String avgLengthQuery = "SELECT AVG(LENGTH(content)) FROM blog WHERE date_created BETWEEN ? AND ?";
        return jdbcTemplate.queryForObject(avgLengthQuery, new Object[]{startTime, endTime}, Integer.class);
    }

    // Additional methods to retrieve other information for the report
}

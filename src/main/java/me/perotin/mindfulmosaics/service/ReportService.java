package me.perotin.mindfulmosaics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getBlogsCreatedWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT COUNT(*) FROM blog WHERE date_created BETWEEN ? AND ?";
        System.out.println("Sql: " + startTime + " " + endTime);
        return jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, Integer.class);
    }

    public int getUsersRegisteredWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT COUNT(*) FROM users WHERE created_at BETWEEN ? AND ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, Integer.class);
    }

    public int getTagsCreatedWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT COUNT(*) FROM tags WHERE created_at BETWEEN ? AND ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, Integer.class);
    }

//    public String getMostUsedTagNameWithin(String timeFrame) {
//        LocalDateTime endTime = LocalDateTime.now();
//        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);
//
//        String sql = "SELECT name, COUNT(*) as tag_count FROM tags " +
//                "WHERE created_at BETWEEN ? AND ? " +
//                "GROUP BY name " +
//                "ORDER BY tag_count DESC " +
//                "LIMIT 1";
//        return jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, (rs, rowNum) -> rs.getString("name"));
//    }

    public String getMostUsedTagNameWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT name, COUNT(*) as tag_count FROM tags " +
                "WHERE created_at BETWEEN ? AND ? " +
                "GROUP BY name " +
                "ORDER BY tag_count DESC " +
                "LIMIT 1";

        // Use query method with a ResultSetExtractor to handle potential empty results
        List<String> tagNames = jdbcTemplate.query(
                sql,
                new Object[]{startTime, endTime},
                (rs) -> {
                    List<String> names = new ArrayList<>();
                    if (rs.next()) {
                        names.add(rs.getString("name"));
                    }
                    return names;
                });

        return tagNames.isEmpty() ? "No tags in this period" : tagNames.get(0);
    }



    public int getLikesWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT COUNT(*) FROM likes WHERE liked_at BETWEEN ? AND ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, Integer.class);
    }

    public int getBlogsDeletedWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT COUNT(*) FROM deletion_logs WHERE deleted_at BETWEEN ? AND ?";
        System.out.println("Sql for deleted blogs: " + startTime + " " + endTime);
        return jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, Integer.class);
    }


    public double getAverageBlogLengthCreatedWithin(String timeFrame) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = getStartTimeBasedOnTimeFrame(timeFrame, endTime);

        String sql = "SELECT AVG(word_count) FROM blog WHERE date_created BETWEEN ? AND ?";

        // We're using queryForObject with Double.class because the average can be a non-integer value
        Double average = jdbcTemplate.queryForObject(sql, new Object[]{startTime, endTime}, Double.class);

        // Check if the result is null (which can happen if no blogs were created in the period)
        // and return 0 to signify no data, or the average value if it's not null
        return average != null ? average : 0;
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

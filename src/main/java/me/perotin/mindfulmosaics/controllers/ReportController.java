package me.perotin.mindfulmosaics.controllers;

import me.perotin.mindfulmosaics.models.Blog;
import me.perotin.mindfulmosaics.models.Report;
import me.perotin.mindfulmosaics.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<Report> generateReport(@RequestParam String timeFrame) {
        int blogsCount = reportService.getBlogsCreatedWithin(timeFrame);
        int usersCount = reportService.getUsersRegisteredWithin(timeFrame);
        int likesCount = reportService.getLikesWithin(timeFrame);
        int blogsDeleted = reportService.getBlogsDeletedWithin(timeFrame);
        double averageBlogLength = reportService.getAverageBlogLengthCreatedWithin(timeFrame);
        int tagsCreated = reportService.getTagsCreatedWithin(timeFrame);
        String mostUsed = reportService.getMostUsedTagNameWithin(timeFrame);

        System.out.println("Blogs deleted: " + blogsDeleted);
        Report report = new Report(usersCount, blogsCount, likesCount, blogsDeleted, averageBlogLength, tagsCreated, mostUsed);
        return ResponseEntity.ok(report);
    }
}

package com.hangeoreum.hanback.course.history.controller;

import com.hangeoreum.hanback.course.history.CourseSearchHistoryService;
import com.hangeoreum.hanback.course.history.dto.CourseSearchHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseSearchHistoryController {

    private final CourseSearchHistoryService courseSearchHistoryService;

    @GetMapping("/users/{userId}/course-search-history")
    public ResponseEntity<List<CourseSearchHistoryResponseDto>> getUserCourseSearchHistory(@PathVariable Long userId) {
        List<CourseSearchHistoryResponseDto> history = courseSearchHistoryService.getUserCourseSearchHistory(userId);
        return ResponseEntity.ok(history);
    }

    @DeleteMapping("/course-search-history/{historyId}")
    public ResponseEntity<String> deleteCourseSearchHistoryEntry(@PathVariable Long historyId) {
        courseSearchHistoryService.deleteCourseSearchHistoryEntry(historyId);
        return ResponseEntity.ok("Course search history entry deleted successfully.");
    }
}

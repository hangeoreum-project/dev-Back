package com.hangeoreum.hanback.course.controller;

import com.hangeoreum.hanback.course.CourseService;
import com.hangeoreum.hanback.course.dto.CourseCreateRequestDto;
import com.hangeoreum.hanback.course.dto.CourseResponseDto;
import com.hangeoreum.hanback.course.dto.CourseSearchRequestDto;
import com.hangeoreum.hanback.course.dto.CourseUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long courseId) {
        CourseResponseDto course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseResponseDto>> searchCourses(@ModelAttribute CourseSearchRequestDto searchRequest) {
        List<CourseResponseDto> courses = courseService.searchCourses(searchRequest);
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseCreateRequestDto requestDto) {
        CourseResponseDto createdCourse = courseService.createCourse(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long courseId, @RequestBody CourseUpdateRequestDto requestDto) {
        CourseResponseDto updatedCourse = courseService.updateCourse(courseId, requestDto);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully.");
    }
}

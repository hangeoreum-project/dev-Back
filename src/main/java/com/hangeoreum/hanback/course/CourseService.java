package com.hangeoreum.hanback.course;

import com.hangeoreum.hanback.course.dto.CourseCreateRequestDto;
import com.hangeoreum.hanback.course.dto.CourseResponseDto;
import com.hangeoreum.hanback.course.dto.CourseSearchRequestDto;
import com.hangeoreum.hanback.course.dto.CourseUpdateRequestDto;
import com.hangeoreum.hanback.domain.course.Course;
import com.hangeoreum.hanback.domain.course.CourseRepository;
import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import com.hangeoreum.hanback.domain.tourist.TouristSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;
    private final TouristSpotRepository touristSpotRepository;

    public CourseResponseDto getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));
        return CourseResponseDto.from(course);
    }

    public List<CourseResponseDto> searchCourses(CourseSearchRequestDto searchRequest) {
        List<Course> courses;
        String keyword = searchRequest.getKeyword();
        String region = searchRequest.getRegion();
        String difficulty = searchRequest.getDifficulty();
        String type = searchRequest.getType();

        if (keyword != null && !keyword.isEmpty()) {
            if (region != null && !region.isEmpty() && difficulty != null && !difficulty.isEmpty() && type != null && !type.isEmpty()) {
                courses = courseRepository.findByNameContainingOrDescriptionContainingAndRegionAndDifficultyAndType(keyword, keyword, region, difficulty, type);
            } else if (region != null && !region.isEmpty() && difficulty != null && !difficulty.isEmpty()) {
                courses = courseRepository.findByNameContainingOrDescriptionContainingAndRegionAndDifficulty(keyword, keyword, region, difficulty);
            } else if (region != null && !region.isEmpty() && type != null && !type.isEmpty()) {
                courses = courseRepository.findByNameContainingOrDescriptionContainingAndRegionAndType(keyword, keyword, region, type);
            } else if (difficulty != null && !difficulty.isEmpty() && type != null && !type.isEmpty()) {
                courses = courseRepository.findByNameContainingOrDescriptionContainingAndDifficultyAndType(keyword, keyword, difficulty, type);
            } else if (region != null && !region.isEmpty()) {
                courses = courseRepository.findByNameContainingOrDescriptionContainingAndRegion(keyword, keyword, region);
            } else if (difficulty != null && !difficulty.isEmpty()) {
                courses = courseRepository.findByNameContainingOrDescriptionContainingAndDifficulty(keyword, keyword, difficulty);
            } else if (type != null && !type.isEmpty()) {
                courses = courseRepository.findByNameContainingOrDescriptionContainingAndType(keyword, keyword, type);
            } else {
                courses = courseRepository.findByNameContainingOrDescriptionContaining(keyword, keyword);
            }
        } else if (region != null && !region.isEmpty()) {
            if (difficulty != null && !difficulty.isEmpty() && type != null && !type.isEmpty()) {
                courses = courseRepository.findByRegionAndDifficultyAndType(region, difficulty, type);
            } else if (difficulty != null && !difficulty.isEmpty()) {
                courses = courseRepository.findByRegionAndDifficulty(region, difficulty);
            } else if (type != null && !type.isEmpty()) {
                courses = courseRepository.findByRegionAndType(region, type);
            } else {
                courses = courseRepository.findByRegion(region);
            }
        } else if (difficulty != null && !difficulty.isEmpty()) {
            if (type != null && !type.isEmpty()) {
                courses = courseRepository.findByDifficultyAndType(difficulty, type);
            } else {
                courses = courseRepository.findByDifficulty(difficulty);
            }
        } else if (type != null && !type.isEmpty()) {
            courses = courseRepository.findByType(type);
        } else {
            courses = courseRepository.findAll();
        }

        return courses.stream()
                .map(CourseResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseResponseDto createCourse(CourseCreateRequestDto requestDto) {
        TouristSpot startSpot = touristSpotRepository.findById(requestDto.getStartSpotId())
                .orElseThrow(() -> new IllegalArgumentException("Start tourist spot not found with ID: " + requestDto.getStartSpotId()));
        TouristSpot endSpot = touristSpotRepository.findById(requestDto.getEndSpotId())
                .orElseThrow(() -> new IllegalArgumentException("End tourist spot not found with ID: " + requestDto.getEndSpotId()));

        Course course = Course.builder()
                .name(requestDto.getName())
                .region(requestDto.getRegion())
                .difficulty(requestDto.getDifficulty())
                .type(requestDto.getType())
                .description(requestDto.getDescription())
                .startSpot(startSpot)
                .endSpot(endSpot)
                .build();

        Course savedCourse = courseRepository.save(course);
        return CourseResponseDto.from(savedCourse);
    }

    @Transactional
    public CourseResponseDto updateCourse(Long courseId, CourseUpdateRequestDto requestDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        TouristSpot startSpot = null;
        if (requestDto.getStartSpotId() != null) {
            startSpot = touristSpotRepository.findById(requestDto.getStartSpotId())
                    .orElseThrow(() -> new IllegalArgumentException("Start tourist spot not found with ID: " + requestDto.getStartSpotId()));
        }

        TouristSpot endSpot = null;
        if (requestDto.getEndSpotId() != null) {
            endSpot = touristSpotRepository.findById(requestDto.getEndSpotId())
                    .orElseThrow(() -> new IllegalArgumentException("End tourist spot not found with ID: " + requestDto.getEndSpotId()));
        }

        course.update(requestDto.getName(), requestDto.getRegion(), requestDto.getDifficulty(),
                requestDto.getType(), requestDto.getDescription(), startSpot, endSpot);

        Course updatedCourse = courseRepository.save(course);
        return CourseResponseDto.from(updatedCourse);
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("Course not found with ID: " + courseId);
        }
        courseRepository.deleteById(courseId);
    }
}

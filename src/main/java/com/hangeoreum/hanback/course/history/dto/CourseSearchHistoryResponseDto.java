package com.hangeoreum.hanback.course.history.dto;

import com.hangeoreum.hanback.domain.course.Course;
import com.hangeoreum.hanback.domain.course.CourseSearchHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearchHistoryResponseDto {
    private Long id;
    private LocalDateTime searchedAt;
    private CourseDto course;

    public static CourseSearchHistoryResponseDto from(CourseSearchHistory history) {
        return CourseSearchHistoryResponseDto.builder()
                .id(history.getId())
                .searchedAt(history.getSearchedAt())
                .course(CourseDto.from(history.getCourse()))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseDto {
        private Long id;
        private String name;

        public static CourseDto from(Course course) {
            return CourseDto.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .build();
        }
    }
}

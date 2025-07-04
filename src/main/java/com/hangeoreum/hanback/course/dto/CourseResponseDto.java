package com.hangeoreum.hanback.course.dto;

import com.hangeoreum.hanback.domain.course.Course;
import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private Long id;
    private String name;
    private String region;
    private String difficulty;
    private String type;
    private String description;
    private TouristSpotDto startSpot;
    private TouristSpotDto endSpot;

    public static CourseResponseDto from(Course course) {
        return CourseResponseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .region(course.getRegion())
                .difficulty(course.getDifficulty())
                .type(course.getType())
                .description(course.getDescription())
                .startSpot(course.getStartSpot() != null ? TouristSpotDto.from(course.getStartSpot()) : null)
                .endSpot(course.getEndSpot() != null ? TouristSpotDto.from(course.getEndSpot()) : null)
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TouristSpotDto {
        private Long id;
        private String title;

        public static TouristSpotDto from(TouristSpot touristSpot) {
            return TouristSpotDto.builder()
                    .id(touristSpot.getId())
                    .title(touristSpot.getTitle())
                    .build();
        }
    }
}

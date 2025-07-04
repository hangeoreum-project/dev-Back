package com.hangeoreum.hanback.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequestDto {
    private String name;
    private String region;
    private String difficulty;
    private String type;
    private String description;
    private Long startSpotId;
    private Long endSpotId;
}

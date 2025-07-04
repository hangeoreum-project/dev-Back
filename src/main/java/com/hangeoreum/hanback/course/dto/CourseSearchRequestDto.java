package com.hangeoreum.hanback.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearchRequestDto {
    private String keyword;
    private String region;
    private String difficulty;
    private String type;
}

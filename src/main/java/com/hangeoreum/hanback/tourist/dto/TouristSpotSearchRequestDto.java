package com.hangeoreum.hanback.tourist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TouristSpotSearchRequestDto {
    private String keyword;
    private String region;
}

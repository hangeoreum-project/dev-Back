package com.hangeoreum.hanback.postcard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostcardCreateRequestDto {
    private String imageUrl;
    private String memo;
    private boolean isPublic;
    private Long userId;
    private Long touristSpotId;
}

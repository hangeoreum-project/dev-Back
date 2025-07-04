package com.hangeoreum.hanback.tourist.dto;

import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TouristSpotResponseDto {
    private Long id;
    private String contentId;
    private String title;
    private String address;
    private Double mapx;
    private Double mapy;
    private String info;

    public static TouristSpotResponseDto from(TouristSpot touristSpot) {
        return TouristSpotResponseDto.builder()
                .id(touristSpot.getId())
                .contentId(touristSpot.getContentId())
                .title(touristSpot.getTitle())
                .address(touristSpot.getAddress())
                .mapx(touristSpot.getMapx())
                .mapy(touristSpot.getMapy())
                .info(touristSpot.getInfo())
                .build();
    }
}

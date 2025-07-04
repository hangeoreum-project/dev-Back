package com.hangeoreum.hanback.postcard.dto;

import com.hangeoreum.hanback.domain.postcard.Postcard;
import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import com.hangeoreum.hanback.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostcardResponseDto {
    private Long id;
    private String imageUrl;
    private String memo;
    private boolean isPublic;
    private UserDto user;
    private TouristSpotDto touristSpot;

    public static PostcardResponseDto from(Postcard postcard) {
        return PostcardResponseDto.builder()
                .id(postcard.getId())
                .imageUrl(postcard.getImageUrl())
                .memo(postcard.getMemo())
                .isPublic(postcard.isPublic())
                .user(UserDto.from(postcard.getUser()))
                .touristSpot(TouristSpotDto.from(postcard.getTouristSpot()))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDto {
        private Long id;
        private String nickname;

        public static UserDto from(User user) {
            return UserDto.builder()
                    .id(user.getId())
                    .nickname(user.getNickname())
                    .build();
        }
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

package com.hangeoreum.hanback.user.dto;

import com.hangeoreum.hanback.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {
    private Long id;
    private String nickname;
    private String email;
    private String profileImageUrl;

    public static UserProfileResponseDto from(User user) {
        return UserProfileResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}

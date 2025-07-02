package com.hangeoreum.hanback.auth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoUser {
    private Long kakaoId;
    private String nickname;
    private String email;
    private String profileImageUrl;
}

package com.hangeoreum.hanback.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.hangeoreum.hanback.auth.model.KakaoUser;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String kakaoId;

    private String nickname;
    private String email;
    private String profileImageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    public static User fromKakaoUser(KakaoUser kakaoUser) {
    return User.builder()
            .kakaoId(String.valueOf(kakaoUser.getKakaoId()))
            .nickname(kakaoUser.getNickname())
            .email(kakaoUser.getEmail())
            .lastLoginAt(LocalDateTime.now())
            .build();
}

    public void updateProfile(String nickname, String email) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (email != null) {
            this.email = email;
        }
    }
}


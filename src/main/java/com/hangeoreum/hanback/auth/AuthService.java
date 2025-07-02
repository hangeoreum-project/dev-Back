package com.hangeoreum.hanback.auth;

import com.hangeoreum.hanback.auth.jwt.JwtTokenProvider;
import com.hangeoreum.hanback.auth.kakao.KakaoOAuthService;
import com.hangeoreum.hanback.auth.model.KakaoUser;
import com.hangeoreum.hanback.domain.user.User;
import com.hangeoreum.hanback.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final KakaoOAuthService kakaoOAuthService;
    private final UserRepository userRepository;

    public String loginWithKakao(String kakaoAccessToken) {
        KakaoUser kakaoUser = kakaoOAuthService.getUserInfo(kakaoAccessToken);
        User user = userRepository.findByKakaoId(String.valueOf(kakaoUser.getKakaoId()))
                .orElseGet(() -> userRepository.save(User.fromKakaoUser(kakaoUser)));

        return JwtTokenProvider.generateToken(user.getId());
    }
}

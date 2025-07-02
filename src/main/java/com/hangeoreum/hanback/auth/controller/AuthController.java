package com.hangeoreum.hanback.auth.controller;

import com.hangeoreum.hanback.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/kakao")
    public String loginWithKakao(@RequestParam String accessToken) {
        return authService.loginWithKakao(accessToken);
    }
}

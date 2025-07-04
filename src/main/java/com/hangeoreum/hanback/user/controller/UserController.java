package com.hangeoreum.hanback.user.controller;

import com.hangeoreum.hanback.user.UserService;
import com.hangeoreum.hanback.user.dto.UserProfileResponseDto;
import com.hangeoreum.hanback.user.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponseDto> getUserProfile(@PathVariable Long userId) {
        UserProfileResponseDto userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserProfileResponseDto> updateUserProfile(@PathVariable Long userId, @RequestBody UserUpdateRequestDto requestDto) {
        UserProfileResponseDto updatedUserProfile = userService.updateUserProfile(userId, requestDto);
        return ResponseEntity.ok(updatedUserProfile);
    }
}

package com.hangeoreum.hanback.user;

import com.hangeoreum.hanback.domain.user.User;
import com.hangeoreum.hanback.domain.user.UserRepository;
import com.hangeoreum.hanback.user.dto.UserProfileResponseDto;
import com.hangeoreum.hanback.user.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponseDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        return UserProfileResponseDto.from(user);
    }

    @Transactional
    public UserProfileResponseDto updateUserProfile(Long userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Update user fields if provided in requestDto
        user.updateProfile(requestDto.getNickname(), requestDto.getEmail());

        // Save the updated user (Spring Data JPA will handle the update if the entity is managed)
        User updatedUser = userRepository.save(user);
        return UserProfileResponseDto.from(updatedUser);
    }
}

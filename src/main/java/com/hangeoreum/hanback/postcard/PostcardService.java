package com.hangeoreum.hanback.postcard;

import com.hangeoreum.hanback.domain.postcard.Postcard;
import com.hangeoreum.hanback.domain.postcard.PostcardRepository;
import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import com.hangeoreum.hanback.domain.tourist.TouristSpotRepository;
import com.hangeoreum.hanback.domain.user.User;
import com.hangeoreum.hanback.domain.user.UserRepository;
import com.hangeoreum.hanback.postcard.dto.PostcardCreateRequestDto;
import com.hangeoreum.hanback.postcard.dto.PostcardPublicStatusUpdateRequestDto;
import com.hangeoreum.hanback.postcard.dto.PostcardResponseDto;
import com.hangeoreum.hanback.postcard.dto.PostcardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostcardService {

    private final PostcardRepository postcardRepository;
    private final UserRepository userRepository;
    private final TouristSpotRepository touristSpotRepository;

    public PostcardResponseDto getPostcardById(Long postcardId) {
        Postcard postcard = postcardRepository.findById(postcardId)
                .orElseThrow(() -> new IllegalArgumentException("Postcard not found with ID: " + postcardId));
        return PostcardResponseDto.from(postcard);
    }

    public List<PostcardResponseDto> getUsersPostcards(Long userId) {
        List<Postcard> postcards = postcardRepository.findByUserId(userId);
        return postcards.stream()
                .map(PostcardResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostcardResponseDto createPostcard(PostcardCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + requestDto.getUserId()));
        TouristSpot touristSpot = touristSpotRepository.findById(requestDto.getTouristSpotId())
                .orElseThrow(() -> new IllegalArgumentException("Tourist spot not found with ID: " + requestDto.getTouristSpotId()));

        Postcard postcard = Postcard.builder()
                .imageUrl(requestDto.getImageUrl())
                .memo(requestDto.getMemo())
                .isPublic(requestDto.isPublic())
                .user(user)
                .touristSpot(touristSpot)
                .build();

        Postcard savedPostcard = postcardRepository.save(postcard);
        return PostcardResponseDto.from(savedPostcard);
    }

    @Transactional
    public PostcardResponseDto updatePostcard(Long postcardId, PostcardUpdateRequestDto requestDto) {
        Postcard postcard = postcardRepository.findById(postcardId)
                .orElseThrow(() -> new IllegalArgumentException("Postcard not found with ID: " + postcardId));

        postcard.update(requestDto.getMemo(), requestDto.getIsPublic());

        Postcard updatedPostcard = postcardRepository.save(postcard);
        return PostcardResponseDto.from(updatedPostcard);
    }

    @Transactional
    public void deletePostcard(Long postcardId) {
        if (!postcardRepository.existsById(postcardId)) {
            throw new IllegalArgumentException("Postcard not found with ID: " + postcardId);
        }
        postcardRepository.deleteById(postcardId);
    }

    @Transactional
    public PostcardResponseDto updatePostcardPublicStatus(Long postcardId, PostcardPublicStatusUpdateRequestDto requestDto) {
        Postcard postcard = postcardRepository.findById(postcardId)
                .orElseThrow(() -> new IllegalArgumentException("Postcard not found with ID: " + postcardId));

        postcard.updatePublicStatus(requestDto.isPublic());

        Postcard updatedPostcard = postcardRepository.save(postcard);
        return PostcardResponseDto.from(updatedPostcard);
    }
}

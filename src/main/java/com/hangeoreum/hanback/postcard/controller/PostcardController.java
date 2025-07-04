package com.hangeoreum.hanback.postcard.controller;

import com.hangeoreum.hanback.postcard.PostcardService;
import com.hangeoreum.hanback.postcard.dto.PostcardCreateRequestDto;
import com.hangeoreum.hanback.postcard.dto.PostcardPublicStatusUpdateRequestDto;
import com.hangeoreum.hanback.postcard.dto.PostcardResponseDto;
import com.hangeoreum.hanback.postcard.dto.PostcardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postcards")
public class PostcardController {

    private final PostcardService postcardService;

    @GetMapping("/{postcardId}")
    public ResponseEntity<PostcardResponseDto> getPostcardById(@PathVariable Long postcardId) {
        PostcardResponseDto postcard = postcardService.getPostcardById(postcardId);
        return ResponseEntity.ok(postcard);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PostcardResponseDto>> getUsersPostcards(@PathVariable Long userId) {
        List<PostcardResponseDto> postcards = postcardService.getUsersPostcards(userId);
        return ResponseEntity.ok(postcards);
    }

    @PostMapping
    public ResponseEntity<PostcardResponseDto> createPostcard(@RequestBody PostcardCreateRequestDto requestDto) {
        PostcardResponseDto createdPostcard = postcardService.createPostcard(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPostcard);
    }

    @PutMapping("/{postcardId}")
    public ResponseEntity<PostcardResponseDto> updatePostcard(@PathVariable Long postcardId, @RequestBody PostcardUpdateRequestDto requestDto) {
        PostcardResponseDto updatedPostcard = postcardService.updatePostcard(postcardId, requestDto);
        return ResponseEntity.ok(updatedPostcard);
    }

    @DeleteMapping("/{postcardId}")
    public ResponseEntity<String> deletePostcard(@PathVariable Long postcardId) {
        postcardService.deletePostcard(postcardId);
        return ResponseEntity.ok("Postcard deleted successfully.");
    }

    @PatchMapping("/{postcardId}/public")
    public ResponseEntity<PostcardResponseDto> updatePostcardPublicStatus(@PathVariable Long postcardId, @RequestBody PostcardPublicStatusUpdateRequestDto requestDto) {
        PostcardResponseDto updatedPostcard = postcardService.updatePostcardPublicStatus(postcardId, requestDto);
        return ResponseEntity.ok(updatedPostcard);
    }
}

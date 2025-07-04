package com.hangeoreum.hanback.tourist.controller;

import com.hangeoreum.hanback.tourist.TouristSpotService;
import com.hangeoreum.hanback.tourist.dto.TouristSpotResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tourist-spots")
public class TouristSpotController {

    private final TouristSpotService touristSpotService;

    @GetMapping("/{spotId}")
    public ResponseEntity<TouristSpotResponseDto> getTouristSpotById(@PathVariable Long spotId) {
        TouristSpotResponseDto touristSpot = touristSpotService.getTouristSpotById(spotId);
        return ResponseEntity.ok(touristSpot);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TouristSpotResponseDto>> searchTouristSpots(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String region) {
        List<TouristSpotResponseDto> touristSpots = touristSpotService.searchTouristSpots(keyword, region);
        return ResponseEntity.ok(touristSpots);
    }
}

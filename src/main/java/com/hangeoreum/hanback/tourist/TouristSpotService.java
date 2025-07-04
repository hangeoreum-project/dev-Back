package com.hangeoreum.hanback.tourist;

import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import com.hangeoreum.hanback.domain.tourist.TouristSpotRepository;
import com.hangeoreum.hanback.tourist.dto.TouristSpotResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TouristSpotService {

    private final TouristSpotRepository touristSpotRepository;

    public TouristSpotResponseDto getTouristSpotById(Long spotId) {
        TouristSpot touristSpot = touristSpotRepository.findById(spotId)
                .orElseThrow(() -> new IllegalArgumentException("Tourist spot not found with ID: " + spotId));
        return TouristSpotResponseDto.from(touristSpot);
    }

    public List<TouristSpotResponseDto> searchTouristSpots(String keyword, String region) {
        List<TouristSpot> touristSpots;
        if (keyword != null && !keyword.isEmpty() && region != null && !region.isEmpty()) {
            touristSpots = touristSpotRepository.findByTitleContainingOrInfoContainingAndAddressContaining(keyword, keyword, region);
        } else if (keyword != null && !keyword.isEmpty()) {
            touristSpots = touristSpotRepository.findByTitleContainingOrInfoContaining(keyword, keyword);
        } else if (region != null && !region.isEmpty()) {
            touristSpots = touristSpotRepository.findByAddressContaining(region);
        } else {
            touristSpots = touristSpotRepository.findAll();
        }
        return touristSpots.stream()
                .map(TouristSpotResponseDto::from)
                .collect(Collectors.toList());
    }
}

package com.hangeoreum.hanback.tourist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import com.hangeoreum.hanback.domain.tourist.TouristSpotRepository;
import com.hangeoreum.hanback.tourist.dto.TouristSpotResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TouristSpotService {

    private final TouristSpotRepository touristSpotRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final WebClient.Builder webClientBuilder;

    @Value("${tour-api.service-key}")
    private String serviceKey;

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

    public List<TouristSpotResponseDto> getNearbyTouristSpots(Double latitude, Double longitude, Integer radius) {
        String cacheKey = "nearby:" + latitude + ":" + longitude + ":" + radius;
        String cachedResult = redisTemplate.opsForValue().get(cacheKey);

        if (cachedResult != null) {
            try {
                return new ObjectMapper().readValue(cachedResult, new com.fasterxml.jackson.core.type.TypeReference<List<TouristSpotResponseDto>>() {});
            } catch (JsonProcessingException e) {
                // Handle exception
            }
        }

        String url = "http://apis.data.go.kr/B551011/KorService1/locationBasedList1";
        Mono<String> response = webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("serviceKey", serviceKey)
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "Tour2025")
                        .queryParam("_type", "json")
                        .queryParam("mapX", longitude)
                        .queryParam("mapY", latitude)
                        .queryParam("radius", radius)
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        String apiResult = response.block();
        List<TouristSpotResponseDto> touristSpots = parseApiResponse(apiResult);

        try {
            String jsonString = new ObjectMapper().writeValueAsString(touristSpots);
            redisTemplate.opsForValue().set(cacheKey, jsonString, 1, TimeUnit.HOURS);
        } catch (JsonProcessingException e) {
            // Handle exception
        }

        return touristSpots;
    }

    /*
    public List<TouristSpotResponseDto> getPopularTouristSpots(String region) {
        // Implement logic for popular tourist spots, possibly using another API or internal data
        return new ArrayList<>();
    }
    */

    private List<TouristSpotResponseDto> parseApiResponse(String apiResponse) {
        List<TouristSpotResponseDto> touristSpots = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(apiResponse);
            JsonNode items = root.path("response").path("body").path("items").path("item");
            for (JsonNode item : items) {
                TouristSpotResponseDto dto = TouristSpotResponseDto.builder()
                        .id(item.path("contentid").asLong())
                        .title(item.path("title").asText())
                        .address(item.path("addr1").asText())
                        .mapx(item.path("mapx").asDouble())
                        .mapy(item.path("mapy").asDouble())
                        .info(item.path("overview").asText(null))
                        .build();
                touristSpots.add(dto);
            }
        } catch (JsonProcessingException e) {
            // Handle exception
        }
        return touristSpots;
    }
}


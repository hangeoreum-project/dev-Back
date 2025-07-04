package com.hangeoreum.hanback.domain.tourist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TouristSpotRepository extends JpaRepository<TouristSpot, Long> {
    List<TouristSpot> findByTitleContainingOrInfoContaining(String titleKeyword, String infoKeyword);
    List<TouristSpot> findByAddressContaining(String region);
    List<TouristSpot> findByTitleContainingOrInfoContainingAndAddressContaining(String titleKeyword, String infoKeyword, String region);
}

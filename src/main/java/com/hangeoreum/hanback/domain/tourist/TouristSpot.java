package com.hangeoreum.hanback.domain.tourist;

import java.time.LocalDateTime;
import jakarta.persistence.*;

public class TouristSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable=false)
    private String contentId;  // 공공 API contentId
    
    @Column(nullable=false)
    private String title;   //관광지 이름
    
    @Column(nullable=false)
    private String address; //주소지
    
    @Column(nullable=false)
    private Double mapx;    //위도
    
    @Column(nullable=false)
    private Double mapy;    //경도
    
    @Column
    private String info; // 관광지 정보

    @Column
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
}
}

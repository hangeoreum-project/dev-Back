package com.hangeoreum.hanback.domain.course;

import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;          // 코스 이름
    private String region;        // 지역명
    private String difficulty;    // 난이도 (예: 쉬움, 보통, 어려움)
    private String type;          // 유형 (자연, 도시 등)
    private String description;   // 설명

    @ManyToOne
    @JoinColumn(name = "start_spot_id")
    private TouristSpot startSpot;

    @ManyToOne
    @JoinColumn(name = "end_spot_id")
    private TouristSpot endSpot;
}

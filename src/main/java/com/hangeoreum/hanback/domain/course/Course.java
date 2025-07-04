package com.hangeoreum.hanback.domain.course;

import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    public void update(String name, String region, String difficulty, String type, String description, TouristSpot startSpot, TouristSpot endSpot) {
        if (name != null) this.name = name;
        if (region != null) this.region = region;
        if (difficulty != null) this.difficulty = difficulty;
        if (type != null) this.type = type;
        if (description != null) this.description = description;
        if (startSpot != null) this.startSpot = startSpot;
        if (endSpot != null) this.endSpot = endSpot;
    }
}

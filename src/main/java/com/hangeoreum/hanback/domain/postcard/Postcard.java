package com.hangeoreum.hanback.domain.postcard;

import com.hangeoreum.hanback.domain.tourist.TouristSpot;
import com.hangeoreum.hanback.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Postcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String memo;
    private boolean isPublic;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tourist_spot_id")
    private TouristSpot touristSpot;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void update(String memo, Boolean isPublic) {
        if (memo != null) {
            this.memo = memo;
        }
        if (isPublic != null) {
            this.isPublic = isPublic;
        }
    }

    public void updatePublicStatus(boolean isPublic) {
        this.isPublic = isPublic;
    }
}

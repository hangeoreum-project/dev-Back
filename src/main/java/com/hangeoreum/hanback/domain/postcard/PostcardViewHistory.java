package com.hangeoreum.hanback.domain.postcard;

import com.hangeoreum.hanback.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostcardViewHistory {

    @EmbeddedId
    private PostcardViewHistoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postcard_id")
    private Postcard postcard;

    private LocalDateTime viewedAt;

    @PrePersist
    public void onCreate() {
        this.viewedAt = LocalDateTime.now();
    }

    public PostcardViewHistory(User user, Postcard postcard) {
        this.user = user;
        this.postcard = postcard;
        this.id = new PostcardViewHistoryId(user.getId(), postcard.getId());
    }
}

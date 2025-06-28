package com.hangeoreum.hanback.domain.postcard;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostcardViewHistoryId implements Serializable {

    private Long user_id;
    private Long postcard_id;
}

package com.hangeoreum.hanback.postcard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostcardUpdateRequestDto {
    private String memo;
    private Boolean isPublic;
}

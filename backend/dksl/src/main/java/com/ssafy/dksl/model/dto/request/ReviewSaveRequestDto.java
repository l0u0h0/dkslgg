package com.ssafy.dksl.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSaveRequestDto {
    private String summonerName;
    private String matchId;
    private String content;
}

package com.ssafy.dksl.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSearchMatchTimelineResponseDto {
    private String matchId;
    private List<String> championNames;
    private List<String> summonerNames;
    private List<TimelineInfoResponseDto> timelines;
}

package com.ssafy.dksl.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimelineInfoResponseDto {
    private int minute;
    private int second;
    private int killerId;
    private int killedId;
    private int x;
    private int y;
}

package com.ssafy.dksl.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationUpdateResponseDto {
    private Long id;
    private String evaluation;
    private int score;
    private LocalDateTime localDateTime;
}

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
public class EvaluationSearchResponseDto {
    private Long id;
    private String evaluation;
    private String evaluatorName;
    private int score;
    private LocalDateTime localDateTime;
}

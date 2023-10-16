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
public class EvaluationSearchInfoResponseDto {
    private float averageScore;
    List<EvaluationSearchResponseDto> evaluations;
}

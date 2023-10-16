package com.ssafy.dksl.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationCreateRequestDto {
    private String evaluation;
    private String evaluatorName;
    private String evaluateeName;
    private int score;
}

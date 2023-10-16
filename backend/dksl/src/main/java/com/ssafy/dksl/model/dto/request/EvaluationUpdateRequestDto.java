package com.ssafy.dksl.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationUpdateRequestDto {
    private Long id;
    private String evaluation;
    private int score;
}

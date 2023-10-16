package com.ssafy.dksl.model.entity;

import com.ssafy.dksl.model.dto.request.EvaluationUpdateRequestDto;
import com.ssafy.dksl.model.dto.response.EvaluationSearchResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Evaluation extends Base {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String evaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluator_id", nullable = false)
    private Member evaluator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluatee_id", nullable = false)
    private Member evaluatee;

    @NotNull
    @Min(1) @Max(5)
    private int score;

    private LocalDateTime deletedAt;

    public EvaluationSearchResponseDto to() {
        return EvaluationSearchResponseDto.builder()
                .evaluatorName(evaluator.getName())
                .score(score)
                .evaluation(evaluation)
                .localDateTime(getUpdatedAt())
                .build();
    }

    public void updateEvaluation(EvaluationUpdateRequestDto dto) {
        this.score = dto.getScore();
        this.evaluation = dto.getEvaluation();
    }

    public void deleteEvaluation() {
        this.deletedAt = LocalDateTime.now();
    }
}

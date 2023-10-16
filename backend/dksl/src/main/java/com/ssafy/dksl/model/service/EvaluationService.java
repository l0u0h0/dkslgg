package com.ssafy.dksl.model.service;

import com.ssafy.dksl.util.exception.EvaluationNotExistException;
import com.ssafy.dksl.util.exception.NoEvaluationException;
import com.ssafy.dksl.util.exception.NonExistSummonerException;
import com.ssafy.dksl.model.dto.request.EvaluationCreateRequestDto;
import com.ssafy.dksl.model.dto.request.EvaluationDeleteRequestDto;
import com.ssafy.dksl.model.dto.request.EvaluationUpdateRequestDto;
import com.ssafy.dksl.model.dto.response.EvaluationSearchInfoResponseDto;
import com.ssafy.dksl.model.dto.response.EvaluationSearchResponseDto;
import com.ssafy.dksl.model.dto.response.EvaluationUpdateResponseDto;
import com.ssafy.dksl.model.entity.Evaluation;

import com.ssafy.dksl.model.entity.Member;
import com.ssafy.dksl.model.repository.EvaluationRepository;
import com.ssafy.dksl.model.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final MemberRepository memberRepository;

    public EvaluationSearchInfoResponseDto findEvaluations(String summonerName, int page) throws NoEvaluationException {
        List<Evaluation> findEvaluations = evaluationRepository.findAllByEvaluateeSummonerName(summonerName, PageRequest.of(page - 1, 10));

        float average = evaluationRepository.findAverageOfEvaluation(summonerName).orElse(0f);

        List<EvaluationSearchResponseDto> evaluationList = findEvaluations.stream().map(evaluation -> evaluation.to()).collect(Collectors.toList());

        return EvaluationSearchInfoResponseDto.builder()
                .evaluations(evaluationList)
                .averageScore(average)
                .build();
    }

    @Transactional
    public boolean createEvaluation(EvaluationCreateRequestDto dto) throws NonExistSummonerException {
        Member evaluatee = memberRepository.findByName(dto.getEvaluateeName()).orElseThrow(() -> new NonExistSummonerException("존재하지 않는 소환사에게 댓글을 등록할 수 없습니다."));
        Member evaluator = memberRepository.findByName(dto.getEvaluatorName()).orElseThrow(() -> new NonExistSummonerException("존재하지 않는 소환사는 댓글을 등록할 수 없습니다."));

        Evaluation savedEvaluation = Evaluation.builder()
                .evaluatee(evaluatee)
                .evaluator(evaluator)
                .evaluation(dto.getEvaluation())
                .score(dto.getScore())
                .build();

        evaluationRepository.save(savedEvaluation);

        return true;
    }

    @Transactional
    public EvaluationUpdateResponseDto updateEvaluation(EvaluationUpdateRequestDto dto) throws EvaluationNotExistException {
        Evaluation updateEvaluation = evaluationRepository.findById(dto.getId()).orElseThrow(() -> new EvaluationNotExistException("존재하지 않는 소환사 평가입니다."));

        updateEvaluation.updateEvaluation(dto);

        return EvaluationUpdateResponseDto.builder()
                .id(updateEvaluation.getId())
                .evaluation(updateEvaluation.getEvaluation())
                .score(updateEvaluation.getScore())
                .localDateTime(updateEvaluation.getUpdatedAt())
                .build();
    }

    @Transactional
    public boolean deleteEvaluation(EvaluationDeleteRequestDto dto) throws EvaluationNotExistException {
        Evaluation deleteEvaluation = evaluationRepository.findById(dto.getId()).orElseThrow(() -> new EvaluationNotExistException("존재하지 않는 소환사 평가입니다."));

        deleteEvaluation.deleteEvaluation();
        return true;
    }
}

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
import com.ssafy.dksl.model.repository.*;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class EvaluationServiceTest {
    @Autowired
    EvaluationService evaluationService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    EntityManager em;

    @DisplayName("특정 소환사의 댓글 정보를 불러온다.")
    @Test
    void findEvaluations() throws InterruptedException, NoEvaluationException {
        // given
        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");

        List<Member> evaluators = new ArrayList<>();
        for(int i = 0; i<5; i++){
            evaluators.add(createMember("testEvaluator" + i, "testEvaluatorPassword" + i, "testEvaluatorPUUID" + i, "testEvaluatorName" + i));
        }

        memberRepository.save(evaluatee);
        memberRepository.saveAll(evaluators);

        for(int i = 0; i<20; i++){
            evaluationRepository.save(createEvaluation(evaluatee, evaluators.get(i % 5), "testEvaluation" + i, i % 5 + 1));
            Thread.sleep(1);
        }

        // when
        EvaluationSearchInfoResponseDto searchInfoResponseDto = evaluationService.findEvaluations(evaluatee.getName(), 1);

        // then
        assertThat(searchInfoResponseDto).isNotNull();

        assertThat(searchInfoResponseDto.getAverageScore()).isEqualTo(3f);

        assertThat(searchInfoResponseDto.getEvaluations()).hasSize(10);

        IntStream.range(0, 10).forEach(idx -> {
            EvaluationSearchResponseDto evaluation = searchInfoResponseDto.getEvaluations().get(idx);
            assertThat(evaluation)
                    .extracting("evaluation", "evaluatorName", "score")
                    .containsExactly("testEvaluation" + (19 - idx), "testEvaluatorName" + (4 - idx % 5), 6 - (idx % 5 + 1));
            assertThat(evaluation.getLocalDateTime()).isNotNull();
        });
    }

    @DisplayName("댓글이 아직 한 개도 달리지 않은 소환사의 평점을 불러오면 예외를 던진다.")
    @Test
    void findEvaluationsWithoutEvaluation() throws InterruptedException, NoEvaluationException {
        // given
        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");

        List<Member> evaluators = new ArrayList<>();
        for(int i = 0; i<5; i++){
            evaluators.add(createMember("testEvaluator" + i, "testEvaluatorPassword" + i, "testEvaluatorPUUID" + i, "testEvaluatorName" + i));
        }

        memberRepository.save(evaluatee);
        memberRepository.saveAll(evaluators);

        // when then
        Assertions.assertThatThrownBy(() -> evaluationService.findEvaluations(evaluatee.getName(), 1))
                .isInstanceOf(NoEvaluationException.class)
                .hasMessage("아직 등록된 댓글이 없습니다.");
    }

    @DisplayName("소환사에게 댓글을 등록한다.")
    @Test
    void createEvaluation() throws NonExistSummonerException {
        // given
        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");
        Member evaluator = createMember("testEvaluator", "testEvaluatorPassword", "testEvaluatorPUUID", "testEvaluatorName");

        memberRepository.save(evaluatee);
        memberRepository.save(evaluator);

        EvaluationCreateRequestDto requestDto = EvaluationCreateRequestDto.builder()
                .evaluateeName("testEvaluateeName")
                .evaluatorName("testEvaluatorName")
                .evaluation("testEvaluation")
                .score(3)
                .build();

        // when
        boolean result = evaluationService.createEvaluation(requestDto);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("존재하지 않는 소환사에게 댓글을 등록하면 예외를 던진다.")
    @Test
    void createEvaluationWithoutEvaluatee() throws NonExistSummonerException {
        // given
        Member evaluator = createMember("testEvaluator", "testEvaluatorPassword", "testEvaluatorPUUID", "testEvaluatorName");

        memberRepository.save(evaluator);

        EvaluationCreateRequestDto requestDto = EvaluationCreateRequestDto.builder()
                .evaluateeName("testEvaluateeName")
                .evaluatorName("testEvaluatorName")
                .evaluation("testEvaluation")
                .score(3)
                .build();

        // when then
        Assertions.assertThatThrownBy(() -> evaluationService.createEvaluation(requestDto))
                .isInstanceOf(NonExistSummonerException.class)
                .hasMessage("존재하지 않는 소환사에게 댓글을 등록할 수 없습니다.");
    }

    @DisplayName("존재하지 않는 소환사가 댓글을 등록하면 예외를 던진다.")
    @Test
    void createEvaluationWithoutEvaluator() throws NonExistSummonerException {
        // given
        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");

        memberRepository.save(evaluatee);

        EvaluationCreateRequestDto requestDto = EvaluationCreateRequestDto.builder()
                .evaluateeName("testEvaluateeName")
                .evaluatorName("testEvaluatorName")
                .evaluation("testEvaluation")
                .score(3)
                .build();

        // when then
        Assertions.assertThatThrownBy(() -> evaluationService.createEvaluation(requestDto))
                .isInstanceOf(NonExistSummonerException.class)
                .hasMessage("존재하지 않는 소환사는 댓글을 등록할 수 없습니다.");
    }

    @DisplayName("소환사에게 등록한 댓글을 수정한다.")
    @Test
    void updateEvaluation() throws EvaluationNotExistException {
        // given
        String beforeEvaluation = "beforeEvaluation";
        String afterEvaluation = "afterEvaluation";

        int beforeScore = 3;
        int afterScore = 4;

        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");
        Member evaluator = createMember("testEvaluator", "testEvaluatorPassword", "testEvaluatorPUUID", "testEvaluatorName");

        memberRepository.save(evaluatee);
        memberRepository.save(evaluator);

        Evaluation savedEvaluation = evaluationRepository.save(createEvaluation(evaluatee, evaluator, beforeEvaluation, beforeScore));

        em.flush();
        em.clear();

        EvaluationUpdateRequestDto requestDto = EvaluationUpdateRequestDto.builder()
                .id(savedEvaluation.getId())
                .evaluation(afterEvaluation)
                .score(afterScore)
                .build();

        // when
        EvaluationUpdateResponseDto responseDto = evaluationService.updateEvaluation(requestDto);

        // then
        assertThat(responseDto).isNotNull()
                .extracting("id", "evaluation", "score")
                .contains(savedEvaluation.getId(), afterEvaluation, afterScore);
    }

    @DisplayName("존재하지 않는 댓글을 수정하면 예외를 던진다.")
    @Test
    void updateNonExistEvaluation() {
        // given
        String evaluation = "afterEvaluation";

        int score = 4;

        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");
        Member evaluator = createMember("testEvaluator", "testEvaluatorPassword", "testEvaluatorPUUID", "testEvaluatorName");

        memberRepository.save(evaluatee);
        memberRepository.save(evaluator);

        EvaluationUpdateRequestDto requestDto = EvaluationUpdateRequestDto.builder()
                .id(1L)
                .evaluation(evaluation)
                .score(score)
                .build();

        // when then
        assertThatThrownBy(() -> evaluationService.updateEvaluation(requestDto))
                .isInstanceOf(EvaluationNotExistException.class)
                .hasMessage("존재하지 않는 소환사 평가입니다.");
    }

    @DisplayName("특정 소환사에 달았던 댓글을 삭제한다.")
    @Test
    void deleteEvaluation() throws EvaluationNotExistException {
        // given
        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");
        Member evaluator = createMember("testEvaluator", "testEvaluatorPassword", "testEvaluatorPUUID", "testEvaluatorName");

        memberRepository.save(evaluatee);
        memberRepository.save(evaluator);

        Evaluation savedEvaluation = evaluationRepository.save(createEvaluation(evaluatee, evaluator, "testEvaluation", 3));

        em.flush();
        em.clear();

        EvaluationDeleteRequestDto requestDto = EvaluationDeleteRequestDto.builder()
                .id(savedEvaluation.getId())
                .build();

        // when
        boolean result = evaluationService.deleteEvaluation(requestDto);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("특정 소환사에 달았던 댓글을 삭제한다.")
    @Test
    void deleteNonExistEvaluation() {
        // given
        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");
        Member evaluator = createMember("testEvaluator", "testEvaluatorPassword", "testEvaluatorPUUID", "testEvaluatorName");

        memberRepository.save(evaluatee);
        memberRepository.save(evaluator);

        EvaluationDeleteRequestDto requestDto = EvaluationDeleteRequestDto.builder()
                .id(1L)
                .build();

        // when then
        Assertions.assertThatThrownBy(() -> evaluationService.deleteEvaluation(requestDto))
                .isInstanceOf(EvaluationNotExistException.class)
                .hasMessage("존재하지 않는 소환사 평가입니다.");
    }

    private Evaluation createEvaluation(Member evaluatee, Member evaluator, String evaluation, int score) {
        return Evaluation.builder()
                .evaluatee(evaluatee)
                .evaluation(evaluation)
                .evaluator(evaluator)
                .score(score)
                .build();
    }

    private Member createMember(String clientId, String password, String puuid, String name) {
        return Member.builder()
                .clientId(clientId)
                .password(password)
                .puuid(puuid)
                .name(name)
                .build();
    }
}
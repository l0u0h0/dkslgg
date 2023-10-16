package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.config.JpaAuditingConfig;
import com.ssafy.dksl.model.entity.Evaluation;
import com.ssafy.dksl.model.entity.Member;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaAuditingConfig.class)
//@Transactional
class EvaluationRepositoryTest {
    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @DisplayName("피평가자의 모든 리뷰를 페이징을 통해 가져온다.")
    @Test
    void findAllByEvaluatee() throws InterruptedException {
        // given
        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");

        List<Member> evaluators = new ArrayList<>();
        for(int i = 0; i<5; i++){
            evaluators.add(createMember("testEvaluator" + i, "testEvaluatorPassword" + i, "testEvaluatorPUUID" + i, "testEvaluatorName" + i));
        }

        memberRepository.save(evaluatee);
        memberRepository.saveAll(evaluators);

        for(int i = 0; i<20; i++){
            Evaluation savedEvaluation = evaluationRepository.save(createEvaluation(evaluatee, evaluators.get(i % 5), i, 1));
            Thread.sleep(1);
        }

        // when
        List<Evaluation> findEvaluations = evaluationRepository.findAllByEvaluateeSummonerName(evaluatee.getName(), PageRequest.of(1, 5));

        // then
        assertThat(findEvaluations).hasSize(5)
                .extracting("evaluation", "score")
                .containsExactly(
                        tuple("testEvaluation14", 1),
                        tuple("testEvaluation13", 1),
                        tuple("testEvaluation12", 1),
                        tuple("testEvaluation11", 1),
                        tuple("testEvaluation10", 1)
                );

        IntStream.range(0, findEvaluations.size())
                .forEach(idx -> {
                            assertThat(findEvaluations.get(idx).getEvaluator()).isNotNull()
                                .extracting("clientId", "password", "puuid", "name")
                                .contains(
                                    "testEvaluator" + (4 - idx), "testEvaluatorPassword" + (4 - idx), "testEvaluatorPUUID" + (4 - idx), "testEvaluatorName" + (4 - idx)
                                );
                            assertThat(findEvaluations.get(idx).getEvaluatee()).isNotNull()
                                    .extracting("clientId", "password", "puuid", "name")
                                    .contains(
                                            "testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName"
                                    );
                        }
                );
    }

    @DisplayName("피평가자의 평균을 구한다.")
    @Test
    void findAverageOfEvaluation() throws InterruptedException {
        // given
        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");

        List<Member> evaluators = new ArrayList<>();
        for(int i = 0; i<5; i++){
            evaluators.add(createMember("testEvaluator" + i, "testEvaluatorPassword" + i, "testEvaluatorPUUID" + i, "testEvaluatorName" + i));
        }

        memberRepository.save(evaluatee);
        memberRepository.saveAll(evaluators);

        for(int i = 0; i<20; i++){
            Evaluation savedEvaluation = evaluationRepository.save(createEvaluation(evaluatee, evaluators.get(i % 5), i, i % 5 + 1));
            Thread.sleep(1);
        }

        // when
        float averageOfEvaluation = evaluationRepository.findAverageOfEvaluation(evaluatee.getName()).orElse(0.0f);

        // then
        Assertions.assertThat(averageOfEvaluation).isEqualTo(3f);
    }

    @DisplayName("평가가 없을 때 피평가자의 평균을 구한다.")
    @Test
    void findAverageOfEvaluationWithoutEvaluation() throws InterruptedException {
        // given
        Member evaluatee = createMember("testEvaluatee", "testEvaluateePassword", "testEvaluateePUUID", "testEvaluateeName");

        List<Member> evaluators = new ArrayList<>();
        for(int i = 0; i<5; i++){
            evaluators.add(createMember("testEvaluator" + i, "testEvaluatorPassword" + i, "testEvaluatorPUUID" + i, "testEvaluatorName" + i));
        }

        memberRepository.save(evaluatee);
        memberRepository.saveAll(evaluators);

        // when
        float average = evaluationRepository.findAverageOfEvaluation(evaluatee.getName()).orElse(0.0f);

        // then
        Assertions.assertThat(average).isEqualTo(0.0f);
    }

    private Evaluation createEvaluation(Member evaluatee, Member evaluator, int i, int score) {
        return Evaluation.builder()
                .evaluatee(evaluatee)
                .evaluation("testEvaluation" + i)
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
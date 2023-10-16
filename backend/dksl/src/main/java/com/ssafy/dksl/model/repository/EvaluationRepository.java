package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.model.entity.Evaluation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    @Query("select e from Evaluation e join fetch e.evaluatee as evtee join fetch e.evaluator as evtor where e.deletedAt is null and e.evaluatee.name = :summonerName order by e.createdAt desc")
    List<Evaluation> findAllByEvaluateeSummonerName(@Param("summonerName") String summonerName, Pageable pageable);

    @Query("select avg(e.score) from Evaluation e join e.evaluatee where e.deletedAt is null and e.evaluatee.name = :summonerName")
    Optional<Float> findAverageOfEvaluation(@Param("summonerName") String summonerName);
}

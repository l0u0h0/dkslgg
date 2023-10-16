package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.model.entity.LbtiAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LbtiAnswerRepository extends JpaRepository<LbtiAnswer, Long> {
}

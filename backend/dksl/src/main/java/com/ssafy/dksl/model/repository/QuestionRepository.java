package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.model.entity.LbtiQuestion;
import com.ssafy.dksl.model.entity.Member;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<LbtiQuestion, Long> {
}

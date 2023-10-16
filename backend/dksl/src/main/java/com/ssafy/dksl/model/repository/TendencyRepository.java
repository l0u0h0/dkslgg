package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.model.entity.Tendency;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TendencyRepository extends JpaRepository<Tendency, String> {
    Optional<Tendency> findByInitial(char initial) throws DataAccessException;
}

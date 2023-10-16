package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.model.entity.Lbti;
import com.ssafy.dksl.model.entity.Tendency;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LbtiRepository extends JpaRepository<Lbti, Long> {
    Optional<Lbti> findByFirstTendencyAndSecondTendency(Tendency first, Tendency second) throws DataAccessException;
    Optional<Lbti> findByFirstTendencyAndSecondTendencyAndThirdTendencyAndFourthTendency(Tendency first, Tendency second, Tendency third, Tendency fourth) throws DataAccessException;
}

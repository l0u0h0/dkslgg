package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByNameAndSubmitAtIsNotNull(String name);
    List<Team> findAllByNameContainingOrDescriptionContainingAndSubmitAtIsNotNull(String searchName, String searchDescription);
    List<Team> findAllBySubmitAtIsNotNullOrderByNameAsc();


}

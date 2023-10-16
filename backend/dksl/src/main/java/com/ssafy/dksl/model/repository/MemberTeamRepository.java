package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.model.entity.Member;
import com.ssafy.dksl.model.entity.MemberTeam;
import com.ssafy.dksl.model.entity.Team;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberTeamRepository extends JpaRepository<MemberTeam, Long> {
    List<MemberTeam> findAllByOrderByUpdatedAtDesc() throws DataAccessException;
    Optional<MemberTeam> findByMemberAndTeam(Member member, Team team) throws DataAccessException;
}

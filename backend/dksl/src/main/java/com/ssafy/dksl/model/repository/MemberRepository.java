package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.model.entity.Member;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByClientId(String clientId) throws DataAccessException;
    Optional<Member> findByName(String name) throws DataAccessException;
}

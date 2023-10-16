package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.model.entity.Member;
import com.ssafy.dksl.config.JpaAuditingConfig;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaAuditingConfig.class)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("클라이언트 아이디로 유저를 찾는다.")
    @Test
    void findByClientId(){
        // given
        String cliendId = "clientId1";

        Member member = Member.builder()
                .clientId(cliendId)
                .deletedAt(LocalDateTime.now())
                .name("testUser1")
                .password("clientPassword")
                .puuid("testPUUID")
                .build();

        Member savedMember = memberRepository.save(member);

        // when
        Member findMember = memberRepository.findByClientId(cliendId).orElse(null);

        // then
        assertThat(findMember).isNotNull()
                .extracting("clientId", "password", "name", "puuid")
                .contains(savedMember.getClientId(), savedMember.getPassword(), savedMember.getName(), savedMember.getPuuid());
    }

    @DisplayName("소환사명으로 유저를 찾는다.")
    @Test
    void findByName(){
        // given
        String gameName = "testUser1";

        Member member = Member.builder()
                .clientId("clientId1")
                .deletedAt(LocalDateTime.now())
                .name(gameName)
                .password("clientPassword")
                .puuid("testPUUID")
                .build();

        Member savedMember = memberRepository.save(member);

        // when
        Member findMember = memberRepository.findByName(gameName).orElse(null);

        // then
        assertThat(findMember).isNotNull()
                .extracting("clientId", "password", "name", "puuid")
                .contains(savedMember.getClientId(), savedMember.getPassword(), savedMember.getName(), savedMember.getPuuid());

    }
}
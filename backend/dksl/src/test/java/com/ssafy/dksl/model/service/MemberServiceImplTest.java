package com.ssafy.dksl.model.service;

import com.ssafy.dksl.model.dto.MemberDto;
import com.ssafy.dksl.model.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${riot.summoner.api.key}")
    private String RIOT_SUMMONER_API_KEY;

    @Value("${riot.summoner.api.uri}")
    private String RIOT_SUMMONER_API_URI;

    @DisplayName("유저 등록이 정상적으로 수행됩니다.")
    @Test
    void register(){
        // given
        Team team = new Team();


//        MemberDto memberDto = MemberDto.builder()
//                .id(1L)
//                .clientId("testClientId")
//                .password("testPassword")
//                .name("testName")
//                .puuid("testPUUID")
//                .build();

        // when

        // then

    }
}
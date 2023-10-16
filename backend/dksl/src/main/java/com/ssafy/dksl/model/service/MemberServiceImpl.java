package com.ssafy.dksl.model.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ssafy.dksl.model.dto.command.member.LoginCommand;
import com.ssafy.dksl.model.dto.command.common.TokenCommand;
import com.ssafy.dksl.model.dto.command.member.RegisterCommand;
import com.ssafy.dksl.model.dto.command.common.UpdateSummonerCommand;
import com.ssafy.dksl.model.dto.response.member.LoginResponse;
import com.ssafy.dksl.model.dto.response.member.MemberResponse;
import com.ssafy.dksl.model.dto.response.common.SummonerResponse;
import com.ssafy.dksl.model.entity.Member;
import com.ssafy.dksl.model.entity.RefreshToken;
import com.ssafy.dksl.model.entity.Tier;
import com.ssafy.dksl.model.repository.RefreshTokenRepository;
import com.ssafy.dksl.model.repository.MemberRepository;
import com.ssafy.dksl.model.repository.TierRepository;
import com.ssafy.dksl.model.service.common.RiotServiceImpl;
import com.ssafy.dksl.util.JwtUtil;
import com.ssafy.dksl.util.data.RankData;
import com.ssafy.dksl.util.exception.*;
import com.ssafy.dksl.util.exception.common.CustomException;
import com.ssafy.dksl.util.exception.common.InvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberServiceImpl extends RiotServiceImpl implements MemberService, RankData {
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;
    private final String BASE_IMG_URI;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TierRepository tierRepository;


    @Autowired
    MemberServiceImpl(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, @Value("${base.img.uri}") String baseImgUri, MemberRepository memberRepository, RefreshTokenRepository refreshTokenRepository, TierRepository tierRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.BASE_IMG_URI = baseImgUri;

        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.tierRepository = tierRepository;
    }

    public boolean register(RegisterCommand registerCommand) throws CustomException {
        // 아이디 가입 여부 확인
        if (memberRepository.findByClientId(registerCommand.getClientId()).isPresent())
            throw new MemberDuplicateException();

        // 닉네임 가입 여부 확인
        if (memberRepository.findByName(registerCommand.getName()).isPresent()) throw new SummonerDuplicateException();

        JsonNode summonerNode = findSummonerByName(registerCommand.getName());  // 회원 PUUID 찾기
        JsonNode leagueNode = findLeagueBySummonerId(summonerNode.get("id").asText());  // 회원 티어, 랭크 찾기

        Tier tier = tierRepository.findById("unranked").orElseThrow(() -> new InvalidException("티어"));
        int rank = 0;
        if(leagueNode.size() != 0) {
            try {
                tier = tierRepository.findById(leagueNode.get(0).get("tier").asText().toLowerCase()).orElseThrow(() -> new RuntimeException("티어의 아이디가 잘못 되었습니다."));
                rank = RankData.rank.get(leagueNode.get(0).get("rank").asText());
                if (rank <= 0 || 5 < rank) throw new RuntimeException("랭크의 숫자가 잘못 되었습니다.");  // 랭크 잘못 가져왔을 경우
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new InvalidException("티어");
            }
        }

        try {
            Member member = Member.builder()
                    .clientId(registerCommand.getClientId())
                    .password(passwordEncoder.encode(registerCommand.getPassword()))
                    .name(registerCommand.getName()).puuid(summonerNode.get("puuid").asText())
                    .phone(registerCommand.getPhone().replace("-", ""))
                    .email(registerCommand.getEmail()).tier(tier).rank(rank)
                    .profileIconId(summonerNode.get("profileIconId").asInt())
                    .level(summonerNode.get("summonerLevel").asInt())
                    .build();

            memberRepository.save(member);

            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MemberCreateException();
        }
    }

    @Override
    public LoginResponse login(LoginCommand loginCommand) throws CustomException {
        // 가입되어 있는 아이디인지 확인
        Member member = memberRepository.findByClientId(loginCommand.getClientId()).orElseThrow(LoginInvalidException::new);
        MemberResponse memberResponse = null;

        // 비밀번호 일치하는지 확인
        if (!passwordEncoder.matches(loginCommand.getPassword(), member.getPassword())) {
            throw new LoginInvalidException();
        }

        // 중복 로그인 되었는지 확인 (redis에 refreshToken 존재하는지)
//        if (refreshTokenRepository.findById(member.getClientId()).isPresent()) {
//            throw new LoginDuplicateException();
//        }

        memberResponse = updateMember(member);  // Member 정보 가져오기

        // Access 토큰 발급
        String accessToken = jwtUtil.generateToken(member.getClientId(), "ROLE_USER", false);

        try {  // Refresh 토큰 발급 후 Redis에 저장
            RefreshToken refreshToken = RefreshToken.builder()
                    .clientId(member.getClientId())
                    .refreshToken(jwtUtil.generateToken(member.getClientId(), "ROLE_USER", true))
                    .build();

            refreshTokenRepository.save(refreshToken);

            return LoginResponse.builder().memberResponse(memberResponse).accessToken(accessToken).refreshToken(refreshToken.getRefreshToken()).build();
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TokenCreateException();
        }
    }

    public MemberResponse getMember(TokenCommand tokenCommand) throws CustomException {
        // 회원 찾기
        try {
            Member member = memberRepository.findByClientId(jwtUtil.getClientId(tokenCommand.getToken())).orElseThrow(MemberNotFoundException::new);
            return updateMember(member);
        } catch (CustomException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean logout(TokenCommand tokenCommand) throws CustomException {
        RefreshToken refreshToken = refreshTokenRepository.findById(jwtUtil.getClientId(tokenCommand.getToken())).orElseThrow(LogoutInvalidException::new);
        refreshTokenRepository.delete(refreshToken);

        return true;
    }

    @Override
    public SummonerResponse updateSummoner(UpdateSummonerCommand updateSummonerCommand) throws CustomException {
        Member member = memberRepository.findByName(updateSummonerCommand.getName()).orElseThrow(MemberNotFoundException::new);
        MemberResponse memberResponse = updateMember(member);
        try {
            return SummonerResponse.builder()
                    .name(memberResponse.getName())
                    .tier(memberResponse.getTier())
                    .rank(memberResponse.getRank())
                    .profileIconId(memberResponse.getProfileIconId())
                    .level(memberResponse.getLevel())
                    .build();
        } catch (Exception e) {
            throw new SummonerInvalidException();
        }
    }

    @Override
    public String reissue(TokenCommand tokenCommand) throws CustomException {
        // refresh 토큰 만료 확인
        String clientId = null;
        try {
            clientId = jwtUtil.getClientId(jwtUtil.getToken(tokenCommand.getToken()));
        } catch(Exception e) {
            log.error("토큰에서 아이디를 가져올 수 없습니다.");
            throw new TokenInvalidException();
        }
        RefreshToken refreshToken = refreshTokenRepository.findById(clientId).orElseThrow(LogoutInvalidException::new);

        // refresh 토큰 검증을 통한 access 토큰 재발급
        try {
            return jwtUtil.generateToken(jwtUtil.getClientId(refreshToken.getRefreshToken()), "ROLE_USER", false);
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TokenCreateException();
        }
    }

    @Override
    public MemberResponse updateMember(Member member) throws CustomException {
        JsonNode summonerNode = findSummonerByName(member.getName());  // 회원 PUUID 찾기
        JsonNode leagueNode = findLeagueBySummonerId(summonerNode.get("id").asText());  // 회원 티어, 랭크 찾기

        Tier tier = tierRepository.findById("unranked").orElseThrow(() -> new InvalidException("티어"));
        int rank = 0;
        if(leagueNode.size() != 0) {
            try {
                tier = tierRepository.findById(leagueNode.get(0).get("tier").asText().toLowerCase()).orElseThrow(() -> new RuntimeException("티어의 아이디가 잘못 되었습니다."));
                rank = RankData.rank.get(leagueNode.get(0).get("rank").asText());
                if (rank <= 0 || 5 < rank) throw new RuntimeException("랭크의 숫자가 잘못 되었습니다.");  // 랭크 잘못 가져왔을 경우
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new InvalidException("티어");
            }
        }

        try {
            member = Member.builder()
                    .id(member.getId())
                    .clientId(member.getClientId())
                    .password(member.getPassword())
                    .name(member.getName())
                    .puuid(summonerNode.get("puuid").asText())  // puuid 정보 업데이트
                    .phone(member.getPhone().replace("-", ""))
                    .email(member.getEmail())
                    .lbti(member.getLbti())
                    .tier(tier)  // 티어 정보 업데이트
                    .rank(rank)  // 랭크 정보 업데이트
                    .profileIconId(summonerNode.get("profileIconId").asInt())  // 프로필 아이콘 정보 업데이트
                    .level(summonerNode.get("summonerLevel").asInt())  // 레벨 정보 업데이트
                    .build();

            memberRepository.save(member);

            return MemberResponse.builder()
                    .name(member.getName())
                    .phone(member.getPhone())
                    .email(member.getEmail())
                    .tier(member.getTier().toTierResponse())
                    .rank(member.getRank())
                    .profileIconId(member.getProfileIconId())
                    .level(member.getLevel())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MemberCreateException();
        }
    }
}

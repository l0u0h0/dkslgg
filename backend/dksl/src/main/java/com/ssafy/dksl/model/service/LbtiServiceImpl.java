package com.ssafy.dksl.model.service;

import com.ssafy.dksl.model.dto.command.common.TokenCommand;
import com.ssafy.dksl.model.dto.command.lbti.SetLbtiCommand;
import com.ssafy.dksl.model.dto.command.team.SearchTeamCommand;
import com.ssafy.dksl.model.dto.response.lbti.LbtiResponse;
import com.ssafy.dksl.model.dto.response.lbti.LbtiQuestionResponse;
import com.ssafy.dksl.model.entity.*;
import com.ssafy.dksl.model.repository.*;
import com.ssafy.dksl.util.JwtUtil;
import com.ssafy.dksl.util.exception.MemberNotFoundException;
import com.ssafy.dksl.util.exception.common.CustomException;
import com.ssafy.dksl.util.exception.common.InvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LbtiServiceImpl implements LbtiService {

    private final JwtUtil jwtUtil;
    private final QuestionRepository questionRepository;
    private final LbtiAnswerRepository lbtiAnswerRepository;
    private final TendencyRepository tendencyRepository;
    private final LbtiRepository lbtiRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public LbtiServiceImpl(JwtUtil jwtUtil, QuestionRepository questionRepository, LbtiAnswerRepository lbtiAnswerRepository, TendencyRepository tendencyRepository, LbtiRepository lbtiRepository, MemberRepository memberRepository) {
        this.jwtUtil = jwtUtil;
        this.questionRepository = questionRepository;
        this.lbtiAnswerRepository = lbtiAnswerRepository;
        this.tendencyRepository = tendencyRepository;
        this.lbtiRepository = lbtiRepository;
        this.memberRepository = memberRepository;
    }

    public List<LbtiQuestionResponse> getQuestionList() throws CustomException {
        List<LbtiQuestion> lbtiQuestionList = questionRepository.findAll();
        if (lbtiQuestionList.size() == 0) {
            throw new InvalidException("롤BTI");
        }
        List<LbtiQuestionResponse> questionResponseList = new ArrayList<>();

        try {
            for (LbtiQuestion question : lbtiQuestionList) {
                questionResponseList.add(question.toQuestionResponse());
            }

            return questionResponseList;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InvalidException("롤BTI");
        }
    }

    @Override
    public LbtiResponse setLbti(SetLbtiCommand setLbtiCommand) throws CustomException {
        Map<Character, Integer> sumLbti = new HashMap<>();

        LbtiAnswer curLbtiAnswer;
        for (long index : setLbtiCommand.getSelectedIndexList()) {
            curLbtiAnswer = lbtiAnswerRepository.findById(index).orElseThrow(() -> new InvalidException("롤BTI"));
            sumLbti.put(curLbtiAnswer.getTendency().getInitial(), sumLbti.getOrDefault(curLbtiAnswer.getTendency().getInitial(), 0) + curLbtiAnswer.getScore());
        }

        Tendency first = tendencyRepository.findByInitial(
                (sumLbti.getOrDefault('C', 0) >= sumLbti.getOrDefault('S', 0)) ? 'C' : 'S'
        ).orElseThrow(() -> new InvalidException("롤BTI"));
        Tendency second = tendencyRepository.findByInitial(
                (sumLbti.getOrDefault('M', 0) >= sumLbti.getOrDefault('V', 0)) ? 'M' : 'V'
        ).orElseThrow(() -> new InvalidException("롤BTI"));
        Tendency third = tendencyRepository.findByInitial(
                (sumLbti.getOrDefault('E', 0) >= sumLbti.getOrDefault('L', 0)) ? 'E' : 'L'
        ).orElseThrow(() -> new InvalidException("롤BTI"));
        Tendency fourth = tendencyRepository.findByInitial(
                (sumLbti.getOrDefault('K', 0) >= sumLbti.getOrDefault('D', 0)) ? 'K' : 'D'
        ).orElseThrow(() -> new InvalidException("롤BTI"));

        Lbti lbti = lbtiRepository.findByFirstTendencyAndSecondTendencyAndThirdTendencyAndFourthTendency(first, second, third, fourth)
                .orElseThrow(() -> new InvalidException("롤BTI"));

        if (setLbtiCommand.getAccessToken() == null || setLbtiCommand.getAccessToken().trim().equals("")) return lbti.toLbtiResponse();
        Member member = memberRepository.findByClientId(jwtUtil.getClientId(jwtUtil.getToken(setLbtiCommand.getAccessToken()))).orElseThrow(MemberNotFoundException::new);
        memberRepository.save(Member.builder()
                .id(member.getId())
                .clientId(member.getClientId())
                .password(member.getPassword())
                .name(member.getName())
                .puuid(member.getPuuid())
                .phone(member.getPhone().replace("-", ""))
                .email(member.getEmail())
                .tier(member.getTier())
                .rank(member.getRank())
                .profileIconId(member.getProfileIconId())
                .level(member.getLevel())
                .lbti(lbti)
                .build());

        return lbti.toLbtiResponse();
    }

    @Override
    public LbtiResponse getLbti(SearchTeamCommand searchTeamCommand) throws CustomException {
        // 회원 확인
        Member member = memberRepository.findByName(searchTeamCommand.getSearchStr()).orElseThrow(MemberNotFoundException::new);

        if(member.getLbti() == null) {
            return null;
        }

        return member.getLbti().toLbtiResponse();
    }
}

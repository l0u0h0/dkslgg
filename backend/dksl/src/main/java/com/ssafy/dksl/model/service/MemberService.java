package com.ssafy.dksl.model.service;

import com.ssafy.dksl.model.dto.command.member.LoginCommand;
import com.ssafy.dksl.model.dto.command.common.TokenCommand;
import com.ssafy.dksl.model.dto.command.member.RegisterCommand;
import com.ssafy.dksl.model.dto.command.common.UpdateSummonerCommand;
import com.ssafy.dksl.model.dto.response.member.LoginResponse;
import com.ssafy.dksl.model.dto.response.member.MemberResponse;
import com.ssafy.dksl.model.dto.response.common.SummonerResponse;
import com.ssafy.dksl.model.entity.Member;
import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    boolean register(RegisterCommand registerCommand) throws CustomException;
    LoginResponse login(LoginCommand loginCommand) throws CustomException;
    MemberResponse getMember(TokenCommand tokenCommand) throws CustomException;
    boolean logout(TokenCommand tokenCommand) throws CustomException;
    SummonerResponse updateSummoner(UpdateSummonerCommand updateSummonerCommand) throws CustomException;
    MemberResponse updateMember(Member member) throws CustomException;
    String reissue(TokenCommand tokenCommand) throws CustomException;
}

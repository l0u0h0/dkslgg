package com.ssafy.dksl.model.service;


import com.ssafy.dksl.model.dto.command.common.TokenCommand;
import com.ssafy.dksl.model.dto.command.lbti.SetLbtiCommand;
import com.ssafy.dksl.model.dto.command.team.SearchTeamCommand;
import com.ssafy.dksl.model.dto.response.lbti.LbtiQuestionResponse;
import com.ssafy.dksl.model.dto.response.lbti.LbtiResponse;
import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LbtiService {
    List<LbtiQuestionResponse> getQuestionList() throws CustomException;

    LbtiResponse setLbti(SetLbtiCommand setLbtiCommand) throws CustomException;
    LbtiResponse getLbti(SearchTeamCommand searchTeamCommand) throws CustomException;
}

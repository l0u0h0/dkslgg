package com.ssafy.dksl.model.dto.response.lbti;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LbtiQuestionResponse {
    private String firstParagraph;
    private String secondParagraph;
    private List<LbtiAnswerResponse> answerList;
}

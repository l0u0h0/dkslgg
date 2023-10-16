package com.ssafy.dksl.model.dto.response.lbti;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LbtiAnswerResponse {
    private long id;
    private String paragraph;
}

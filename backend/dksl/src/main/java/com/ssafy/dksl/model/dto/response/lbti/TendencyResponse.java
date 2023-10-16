package com.ssafy.dksl.model.dto.response.lbti;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TendencyResponse {
    private String id;
    private String name;
    private char initial;
}

package com.ssafy.dksl.model.dto.response.lbti;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
public class LbtiResponse {

    private long id;

    private String name;

    private String description;

    private String championName;

    private TendencyResponse firstTendency;

    private TendencyResponse secondTendency;

    private TendencyResponse thirdTendency;

    private TendencyResponse fourthTendency;
}

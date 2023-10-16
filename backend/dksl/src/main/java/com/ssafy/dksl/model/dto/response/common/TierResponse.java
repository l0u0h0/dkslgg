package com.ssafy.dksl.model.dto.response.common;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TierResponse {
    private String id;
    private String name;
    private int orderNum;
}

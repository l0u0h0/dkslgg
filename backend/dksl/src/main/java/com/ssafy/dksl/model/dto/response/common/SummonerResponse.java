package com.ssafy.dksl.model.dto.response.common;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummonerResponse {
    @NotNull
    private String name;

    @NotNull
    private TierResponse tier;

    @NotNull
    private int rank;

    @NotNull
    private int profileIconId;

    @NotNull
    private int level;
}

package com.ssafy.dksl.model.dto.response.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.dksl.model.dto.response.common.TierResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TeamResponse {
    @NotNull
    private String name;
    @NotNull
    private long memberCount;
    @NotNull
    private String description;
    @JsonProperty(value = "img")
    private byte[] imgByteArray;
    @JsonProperty(value = "avgTier")
    private TierResponse tierResponse;
}

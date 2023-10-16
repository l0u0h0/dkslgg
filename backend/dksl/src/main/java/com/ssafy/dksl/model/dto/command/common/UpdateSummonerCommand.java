package com.ssafy.dksl.model.dto.command.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateSummonerCommand {
    private String name;
}

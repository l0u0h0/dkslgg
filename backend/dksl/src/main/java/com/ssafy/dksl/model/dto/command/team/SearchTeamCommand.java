package com.ssafy.dksl.model.dto.command.team;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchTeamCommand {
    private String searchStr;
}

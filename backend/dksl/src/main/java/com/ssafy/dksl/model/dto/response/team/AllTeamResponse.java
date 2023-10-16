package com.ssafy.dksl.model.dto.response.team;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AllTeamResponse {
    private List<TeamResponse> teamList;
    private List<TeamResponse> recentTeamList;
}

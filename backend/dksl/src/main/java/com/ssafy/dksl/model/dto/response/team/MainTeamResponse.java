package com.ssafy.dksl.model.dto.response.team;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MainTeamResponse {
    private List<TeamResponse> tierTeamList;
    private List<TeamResponse> memberCountTeamList;
    private List<TeamResponse> recentTeamList;
}

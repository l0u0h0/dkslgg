package com.ssafy.dksl.model.dto.response.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.dksl.model.dto.response.lbti.LbtiResponse;
import com.ssafy.dksl.model.dto.response.team.TeamDetailResponse;
import com.ssafy.dksl.model.dto.response.team.TeamResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SummonerTeamResponse {
    @JsonProperty(value = "lbti")
    private LbtiResponse lbtiResponse;
    @JsonProperty(value = "teamList")
    private List<TeamDetailResponse> summonerTeamList;
    private List<TeamResponse> teamRankList;
}

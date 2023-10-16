package com.ssafy.dksl.model.service.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.ssafy.dksl.util.exception.RiotApiInvalidException;
import com.ssafy.dksl.util.exception.SummonerNotFoundException;
import com.ssafy.dksl.util.exception.common.CustomException;

public interface RiotService {
    JsonNode findSummonerByName(String name) throws CustomException;
    JsonNode findLeagueBySummonerId(String summonerId) throws CustomException;
    JsonNode findMatchTimelineByMatchId(String matchId) throws CustomException;
    JsonNode findSummonerByPuuid(String encryptedPuuid) throws CustomException;
}

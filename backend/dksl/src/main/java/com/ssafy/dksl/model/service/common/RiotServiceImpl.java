package com.ssafy.dksl.model.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.dksl.util.exception.common.CustomException;
import com.ssafy.dksl.util.exception.RiotApiInvalidException;
import com.ssafy.dksl.util.exception.SummonerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class RiotServiceImpl implements RiotService {

    @Value("${riot.api.key}")
    private String RIOT_API_KEY;

    @Value("${riot.summoner.api.uri}")
    private String RIOT_SUMMONER_API_URI;

    @Value("${riot.league.api.uri}")
    private String RIOT_LEAGUE_API_URI;

    @Value("${riot.matchinfo.api.uri}")
    private String RIOT_MATCHINFO_API_URI;

    @Value("${riot.timeline.api.uri.prefix}")
    private String RIOT_TIMELINE_API_URI_PREFIX;

    @Value("${riot.timeline.api.uri.suffix}")
    private String RIOT_TIMELINE_API_URI_SUFFIX;

    @Value("${riot.summoner.puuid.api.uri}")
    private String RIOT_SUMMONER_PUUID_API_URI;

    public JsonNode findSummonerByName(String name) throws CustomException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .header("X-Riot-Token", RIOT_API_KEY)
                .uri(URI.create(RIOT_SUMMONER_API_URI + URLEncoder.encode(name)))
                .GET().build();

        HttpResponse<String> response;  // Response 받을 변수
        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }

        // 응답 코드 확인
        int statusCode = response.statusCode();
        if (statusCode == HttpStatus.SC_NOT_FOUND) throw new SummonerNotFoundException();  // 닉네임 없음
        else if (statusCode != HttpStatus.SC_OK) throw new RiotApiInvalidException();  // 라이엇 API 호출 실패

        ObjectMapper objectmapper = new ObjectMapper();
        try {
            return objectmapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }
    }

    @Override
    public JsonNode findLeagueBySummonerId(String summonerId) throws CustomException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .header("X-Riot-Token", RIOT_API_KEY)
                .uri(URI.create(RIOT_LEAGUE_API_URI + URLEncoder.encode(summonerId)))
                .GET().build();

        HttpResponse<String> response;
        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }

        // 응답 코드 확인
        int statusCode = response.statusCode();
        if (statusCode != HttpStatus.SC_OK) throw new RiotApiInvalidException();  // 라이엇 API 호출 실패

        ObjectMapper objectmapper = new ObjectMapper();
        try {
            return objectmapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }
    }

    public JsonNode findMatchMemberFromMatchId(String matchId) throws RiotApiInvalidException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest getRequest;
        try{
            getRequest = HttpRequest.newBuilder()
                    .header("X-Riot-Token", RIOT_API_KEY)
                    .uri(URI.create(RIOT_MATCHINFO_API_URI + matchId))
                    .GET().build();
        } catch(IllegalArgumentException e){
            log.error("Invalid RIOT API request at findMatchMemberFromMatchId method!");
            throw new RiotApiInvalidException();
        }


        HttpResponse<String> response;
        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }

        // 응답 코드 확인
        int statusCode = response.statusCode();
        if (statusCode != HttpStatus.SC_OK) {
            log.error("응답 코드: {}", response.body().toString());
            throw new RiotApiInvalidException();  // 라이엇 API 호출 실패
        }

        ObjectMapper objectmapper = new ObjectMapper();
        try {
            return objectmapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }
    }

    public JsonNode findMatchTimelineByMatchId(String matchId) throws RiotApiInvalidException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest getRequest;
        try{
            getRequest = HttpRequest.newBuilder()
                    .header("X-Riot-Token", RIOT_API_KEY)
                    .uri(URI.create(RIOT_TIMELINE_API_URI_PREFIX + matchId + RIOT_TIMELINE_API_URI_SUFFIX))
                    .GET().build();
        } catch (IllegalArgumentException e){
            log.error("Invalid request of RIOT API at findMatchTimelineByMatchId method!");
            throw new RiotApiInvalidException();
        }


        HttpResponse<String> response;
        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }

//        log.info("response: {}", response.body().toString());

        // 응답 코드 확인
        int statusCode = response.statusCode();
        if (statusCode != HttpStatus.SC_OK) throw new RiotApiInvalidException();  // 라이엇 API 호출 실패

        ObjectMapper objectmapper = new ObjectMapper();
        try {
//            if(objectmapper.readTree(response.body())==null) log.info("Cannot read response body!!");
            log.info("body size: {}", response.body().length());
            return objectmapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }
    }

    public JsonNode findSummonerByPuuid(String encryptedPuuid) throws RiotApiInvalidException, SummonerNotFoundException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .header("X-Riot-Token", RIOT_API_KEY)
                .uri(URI.create(RIOT_SUMMONER_PUUID_API_URI + URLEncoder.encode(encryptedPuuid)))
                .GET().build();

        HttpResponse<String> response;  // Response 받을 변수
        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }

        // 응답 코드 확인
        int statusCode = response.statusCode();
        if (statusCode == HttpStatus.SC_NOT_FOUND) throw new SummonerNotFoundException();  // 닉네임 없음
        else if (statusCode != HttpStatus.SC_OK) throw new RiotApiInvalidException();  // 라이엇 API 호출 실패

        ObjectMapper objectmapper = new ObjectMapper();
        try {
            return objectmapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RiotApiInvalidException();
        }
    }
}

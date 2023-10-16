package com.ssafy.dksl.controller;

import com.ssafy.dksl.model.dto.command.member.TeamMemberCommand;
import com.ssafy.dksl.model.dto.command.team.SearchTeamCommand;
import com.ssafy.dksl.model.dto.request.team.CreateTeamRequest;
import com.ssafy.dksl.model.dto.response.team.AllTeamResponse;
import com.ssafy.dksl.model.dto.response.team.MainTeamResponse;
import com.ssafy.dksl.model.dto.response.team.TeamResponse;
import com.ssafy.dksl.model.service.TeamService;
import com.ssafy.dksl.util.exception.common.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    private ResponseEntity<?> createTeam(@RequestHeader("Authorization") String token, @RequestPart(value = "team") CreateTeamRequest createTeamRequest, @RequestPart(value = "img") MultipartFile img) {
        try {
            return ResponseEntity.ok(teamService.createTeam(createTeamRequest.toCreateTeamCommand(token, img)));
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

    @PostMapping("join")
    private ResponseEntity<?> joinMember(@RequestHeader("Authorization") String token, @RequestBody String teamName) {
        try {
            return ResponseEntity.ok(teamService.createTeamMember(TeamMemberCommand.builder().token(token).teamName(teamName).build()));
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

    @PostMapping("leave")
    private ResponseEntity<?> leaveTeamMember(@RequestHeader("Authorization") String token, @RequestBody String teamName) {
        try {
            return ResponseEntity.ok(teamService.leaveTeamMember(TeamMemberCommand.builder().token(token).teamName(teamName).build()));
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

    @GetMapping("main")
    private ResponseEntity<?> getMainTeamList() {  // 최초 한 번 불러오기
        try {
            return ResponseEntity.ok(MainTeamResponse.builder()
                    .tierTeamList(teamService.getTeamRankList())
                    .memberCountTeamList(teamService.getMemberCountTeamList())
                    .recentTeamList(teamService.getRecentTeamList(10))
                    .build());
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

    @GetMapping
    private ResponseEntity<?> getFirstTeamList() {  // 최초 한 번 불러오기
        try {
            AllTeamResponse allTeamResponse = AllTeamResponse.builder()
                    .teamList(teamService.getAllTeamList())  // 모든 팀 리스트
                    .recentTeamList(teamService.getRecentTeamList(3))  // 최근 가입 팀 리스트
                    .build();
            return ResponseEntity.ok(allTeamResponse);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

    @GetMapping("recent")
    private ResponseEntity<?> getRecentTeamList() {
        try {
            return ResponseEntity.ok(teamService.getRecentTeamList(10));
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

    @GetMapping("search")
    private ResponseEntity<?> getSearchTeamList(@RequestParam(value = "word") String searchStr) {
        try {
            return ResponseEntity.ok(teamService.getSearchTeamList(SearchTeamCommand.builder().searchStr(searchStr.trim()).build()));
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

    @GetMapping("{name}")
    private ResponseEntity<?> getTeamDetail(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("name") String teamName) {
        try {
            return ResponseEntity.ok(teamService.getTeamDetail(TeamMemberCommand.builder().token(token).teamName(teamName).build()));
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }
}

package com.ssafy.dksl.controller;

import com.ssafy.dksl.model.dto.command.lbti.SetLbtiCommand;
import com.ssafy.dksl.model.service.LbtiService;
import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lbti")
public class LbtiController {

    private final LbtiService lbtiService;

    @Autowired
    public LbtiController(LbtiService lbtiService) {
        this.lbtiService = lbtiService;
    }

    @GetMapping("question")
    private ResponseEntity<?> getQuestionList() {
        try {
            return ResponseEntity.ok(lbtiService.getQuestionList());
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

    @PostMapping
    private ResponseEntity<?> setLbti(@RequestHeader(value = "Authorization", required = false) String accessToken, @RequestBody List<Long> selectedList) {
        try {
            return ResponseEntity.ok(lbtiService.setLbti(SetLbtiCommand.builder().accessToken(accessToken).selectedIndexList(selectedList).build()));
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }
}

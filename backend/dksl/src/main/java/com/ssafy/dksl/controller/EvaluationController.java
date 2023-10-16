package com.ssafy.dksl.controller;

import com.ssafy.dksl.util.exception.EvaluationNotExistException;
import com.ssafy.dksl.util.exception.NoEvaluationException;
import com.ssafy.dksl.util.exception.NonExistSummonerException;
import com.ssafy.dksl.model.dto.request.EvaluationCreateRequestDto;
import com.ssafy.dksl.model.dto.request.EvaluationDeleteRequestDto;
import com.ssafy.dksl.model.dto.request.EvaluationUpdateRequestDto;
import com.ssafy.dksl.model.dto.response.EvaluationSearchInfoResponseDto;
import com.ssafy.dksl.model.dto.response.EvaluationUpdateResponseDto;
import com.ssafy.dksl.model.service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;

    @GetMapping("/{summonerName}/{page}")
    public ResponseEntity<?> findEvaluations(@PathVariable String summonerName, @PathVariable int page){
        EvaluationSearchInfoResponseDto evaluations;
        try{
            evaluations = evaluationService.findEvaluations(summonerName, page);
        } catch (NoEvaluationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Unknown Exception Thrown!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(evaluations);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvaluation(@RequestBody @Valid EvaluationCreateRequestDto requestDto){
        boolean result;

        try{
            result = evaluationService.createEvaluation(requestDto);
        } catch (NonExistSummonerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            return new ResponseEntity<>("Unknown Exception Thrown!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateEvaluation(@RequestBody @Valid EvaluationUpdateRequestDto requestDto){
        EvaluationUpdateResponseDto responseDto;
        try{
            responseDto = evaluationService.updateEvaluation(requestDto);
        } catch (EvaluationNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            return new ResponseEntity<>("Unknown Exception Thrown!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteEvaluation(@RequestBody @Valid EvaluationDeleteRequestDto requestDto){
        boolean result;
        try{
            result = evaluationService.deleteEvaluation(requestDto);
        } catch(EvaluationNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e){
            return new ResponseEntity<>("Unknown Exception Thrown!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(result);
    }
}

package com.ssafy.dksl.model.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ssafy.dksl.model.dto.response.*;
import com.ssafy.dksl.model.entity.Member;
import com.ssafy.dksl.model.repository.MemberRepository;
import com.ssafy.dksl.model.service.common.RiotServiceImpl;
import com.ssafy.dksl.util.exception.NonExistReviewException;
import com.ssafy.dksl.util.exception.RiotApiInvalidException;
import com.ssafy.dksl.util.exception.SummonerNotFoundException;
import com.ssafy.dksl.util.exception.UserNotExistException;
import com.ssafy.dksl.model.dto.request.ReviewDeleteRequestDto;
import com.ssafy.dksl.model.dto.request.ReviewSaveRequestDto;
import com.ssafy.dksl.model.dto.request.ReviewUpdateRequestDto;
import com.ssafy.dksl.model.entity.Review;
import com.ssafy.dksl.model.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService extends RiotServiceImpl {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    private final int PAGESIZE = 10;
    private final int ONE_MILLISECOND = 1000;
    private final int ONE_MINUTE = 60;
    private final String CHAMPION_KILL = "CHAMPION_KILL";

    public List<ReviewSearchResponseDto> getReviews(String matchId, int page){
        List<Review> reviews = reviewRepository.findByMatchIdAndDeletedAtIsNullOrderByCreatedAtDesc(matchId, PageRequest.of(page, PAGESIZE));

        return reviews.stream().map(Review::to).collect(Collectors.toList());
    }

    @Transactional
    public Review saveReview(ReviewSaveRequestDto reviewSaveRequestDto) throws UserNotExistException{
        String summonerName = reviewSaveRequestDto.getSummonerName();
        Member findMember = memberRepository.findByName(summonerName).orElseThrow(() -> new UserNotExistException("롤 계정이 존재하지 않는 유저입니다."));

        Review savedReview = Review.builder()
                .member(findMember)
                .matchId(reviewSaveRequestDto.getMatchId())
                .content(reviewSaveRequestDto.getContent())
                .build();

        return reviewRepository.save(savedReview);
    }

    @Transactional
    public ReviewUpdateResponseDto updateReview(ReviewUpdateRequestDto reviewUpdateRequestDto) throws NonExistReviewException {
        Review findReview = reviewRepository.findById(reviewUpdateRequestDto.getId()).orElseThrow(() -> new NonExistReviewException("존재하지 않는 댓글입니다."));

        findReview.updateReview(reviewUpdateRequestDto.getContent());

        return ReviewUpdateResponseDto.builder()
                .id(findReview.getId())
                .content(findReview.getContent())
                .matchId(findReview.getMatchId())
                .build();
    }

    /*
     * DB 상에서 삭제하는 것이 아닌 deletedAt 필드에 삭제 시간만을 업데이트하는 것이다.
     */
    @Transactional
    public ReviewDeleteResponseDto deleteReview(ReviewDeleteRequestDto reviewDeleteRequestDto) throws NonExistReviewException {
        Review findReview = reviewRepository.findById(reviewDeleteRequestDto.getId()).orElseThrow(() -> new NonExistReviewException("존재하지 않는 댓글입니다."));

        findReview.deleteReview();

        return ReviewDeleteResponseDto.builder()
                .id(findReview.getId())
                .build();
    }

    public ReviewSearchMatchTimelineResponseDto searchMatchTimeline(String matchId) throws RiotApiInvalidException {
        List<String> championNames = new ArrayList<>();
        List<TimelineInfoResponseDto> timelines = new ArrayList<>();

        // 매치 참가자 정보 가공 과정
        JsonNode matchInfoNode = findMatchMemberFromMatchId(matchId);

        JsonNode infosNode = matchInfoNode.get("info");
        if(infosNode.isNull()){
            throw new RiotApiInvalidException();
        }

        JsonNode participantsArrayNode = infosNode.get("participants");
        if(participantsArrayNode.isNull() || !participantsArrayNode.isArray()){
            throw new RiotApiInvalidException();
        }

        for(JsonNode participantNode : participantsArrayNode) {
            JsonNode championNode = participantNode.get("championName");
            if(championNode.isNull()){
                throw new RiotApiInvalidException();
            }
            championNames.add(championNode.asText());
        }

        // 매치 타임라인 관련 정보 가공 과정
        JsonNode timelineNode = findMatchTimelineByMatchId(matchId);

        JsonNode infoNode = timelineNode.get("info");
        if(infoNode.isNull()) {
            throw new RiotApiInvalidException();
        }

        JsonNode frameArrayNode = infoNode.get("frames");
        if(frameArrayNode.isNull() || !frameArrayNode.isArray()) {
            throw new RiotApiInvalidException();
        }

        for(JsonNode frameNode : frameArrayNode) {
            JsonNode eventsArrayNode = frameNode.get("events");
            if(eventsArrayNode.isNull() || !eventsArrayNode.isArray()) {
                throw new RiotApiInvalidException();
            }

            for(JsonNode eventNode : eventsArrayNode) {
                if (eventNode.get("type").asText().equals(CHAMPION_KILL)) {
                    TimelineInfoResponseDto timeline = createTimelineInfo(eventNode);
                    timelines.add(timeline);
                }
            }
        }

        // 각각의 플레이어의 소환사명을 추출
        JsonNode participantsNodes = infoNode.get("participants");
        if(participantsNodes.isNull() || !participantsNodes.isArray()){
            throw new RiotApiInvalidException();
        }

        ArrayList<String> summonerNames = new ArrayList<>();
        for(JsonNode participantNode : participantsNodes){
            JsonNode puuid = participantNode.get("puuid");
            if(puuid.isNull()){
                throw new RiotApiInvalidException();
            }

            String encryptedPuuid = puuid.asText();
            try{
                JsonNode summonerNode = findSummonerByPuuid(encryptedPuuid);
                String summonerName = summonerNode.get("name").asText();

                summonerNames.add(summonerName);
            } catch(RiotApiInvalidException | SummonerNotFoundException | NullPointerException e){
                throw new RiotApiInvalidException();
            }
        }

        return ReviewSearchMatchTimelineResponseDto.builder()
                .timelines(timelines)
                .championNames(championNames)
                .summonerNames(summonerNames)
                .matchId(matchId)
                .build();
    }

    private TimelineInfoResponseDto createTimelineInfo(JsonNode eventNode) {
        int killerId = eventNode.get("killerId").asInt();
        int x = eventNode.get("position").get("x").asInt();
        int y = eventNode.get("position").get("y").asInt();

        int timestamp = eventNode.get("timestamp").asInt();
        int seconds = timestamp / ONE_MILLISECOND;
        int minute = seconds / ONE_MINUTE;
        int second = seconds % ONE_MINUTE;

        int killedId = eventNode.get("victimId").asInt();

        return TimelineInfoResponseDto.builder()
                .killedId(killedId)
                .killerId(killerId)
                .minute(minute)
                .second(second)
                .x(x)
                .y(y)
                .build();
    }
}

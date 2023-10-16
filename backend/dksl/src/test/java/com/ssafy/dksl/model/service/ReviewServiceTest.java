package com.ssafy.dksl.model.service;

import com.ssafy.dksl.model.entity.Member;
import com.ssafy.dksl.util.exception.NonExistReviewException;
import com.ssafy.dksl.util.exception.UserNotExistException;
import com.ssafy.dksl.model.dto.response.ReviewSearchResponseDto;
import com.ssafy.dksl.model.dto.request.ReviewDeleteRequestDto;
import com.ssafy.dksl.model.dto.request.ReviewSaveRequestDto;
import com.ssafy.dksl.model.dto.request.ReviewUpdateRequestDto;
import com.ssafy.dksl.model.dto.response.ReviewDeleteResponseDto;
import com.ssafy.dksl.model.dto.response.ReviewUpdateResponseDto;
import com.ssafy.dksl.model.entity.Review;
import com.ssafy.dksl.model.repository.ReviewRepository;
import com.ssafy.dksl.model.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class ReviewServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("특정 matchId에 해당하는 모든 댓글을 불러온다.")
    @Test
    void getReviews() throws InterruptedException {
        // given
        String matchId = "KR_66990325";

        Member member = createMember("testClientId", "testPassword", "testName", "testPUUID");

        Member savedMember = memberRepository.save(member);

        for(int i = 0; i< 40; i++) {
            reviewRepository.save(createReview("testContent" + i, matchId, savedMember));
            Thread.sleep(1);
        }

        // when
        List<ReviewSearchResponseDto> reviews = reviewService.getReviews(matchId, 2);

        // then
        assertThat(reviews).hasSize(10)
                .extracting("matchId", "content")
                .containsExactly(
                        tuple(matchId, "testContent19"), tuple(matchId, "testContent18"),
                        tuple(matchId, "testContent17"), tuple(matchId, "testContent16"),
                        tuple(matchId, "testContent15"), tuple(matchId, "testContent14"), tuple(matchId, "testContent13"),
                        tuple(matchId, "testContent12"), tuple(matchId, "testContent11"), tuple(matchId, "testContent10")
                );
    }
    
    @DisplayName("특정 매치를 플레이한 유저가 플레이에 대한 댓글을 단다.")
    @Test
    void saveReview() throws UserNotExistException {
        // given
        String matchId = "KR_66990325";

        Member member = createMember("testClientId", "testPassword", "testName", "testPUUID");
        Member savedUser = memberRepository.save(member);

        ReviewSaveRequestDto requestDto = ReviewSaveRequestDto.builder()
                .clientId(savedUser.getClientId())
                .matchId(matchId)
                .content("testContent")
                .build();

        // when
        Review savedReview = reviewService.saveReview(requestDto);

        // then
        assertThat(savedReview).isNotNull()
                .extracting("matchId", "content", "deletedAt")
                .contains(matchId, "testContent", null);
    }

    @DisplayName("롤 계정이 존재하지 않는 유저가 플레이에 대한 댓글을 작성할 때 예외를 던진다.")
    @Test
    void saveReviewWithNonExistedClientId() {
        // given
        String matchId = "KR_66990325";

        Member member = createMember("existedClientId", "testPassword", "testName", "testPUUID");
        memberRepository.save(member);

        ReviewSaveRequestDto requestDto = ReviewSaveRequestDto.builder()
                .clientId("nonExistedClientId")
                .matchId(matchId)
                .content("testContent")
                .build();

        // when then
        assertThatThrownBy(() -> reviewService.saveReview(requestDto))
                .isInstanceOf(UserNotExistException.class)
                .hasMessage("롤 계정이 존재하지 않는 유저입니다.");
    }

    @DisplayName("이미 작성한 리뷰를 업데이트합니다.")
    @Test
    void updateReview() throws NonExistReviewException {
        String matchId = "KR_66990325";

        Member member = createMember("testClientId", "testPassword", "testName", "testPUUID");
        Member savedUser = memberRepository.save(member);


        Review savedReview = reviewRepository.save(createReview("testContent", matchId, savedUser));

        String newContent = "newContent";

        ReviewUpdateRequestDto requestDto = ReviewUpdateRequestDto.builder()
                .id(savedReview.getId())
                .content(newContent)
                .matchId(matchId)
                .build();

        // when
        ReviewUpdateResponseDto responseDto = reviewService.updateReview(requestDto);

        em.flush();
        em.clear();

        Review checkReview = reviewRepository.findById(savedReview.getId()).orElseThrow(() -> new NonExistReviewException("존재하지 않는 댓글입니다."));

        // then
        assertThat(responseDto).isNotNull()
                .extracting("content", "matchId")
                .contains(checkReview.getContent(), checkReview.getMatchId());
    }

    @DisplayName("작성한 적이 없는 리뷰를 수정하기 때문에 예외를 던집니다.")
    @Test
    void updateNonExistReview() {
        String matchId = "KR_66990325";

        Member member = createMember("testClientId", "testPassword", "testName", "testPUUID");
        Member savedUser = memberRepository.save(member);


        Review savedReview = reviewRepository.save(createReview("testContent", matchId, savedUser));

        String newContent = "newContent";

        Long nonExistReviewId = savedReview.getId() + 1L;

        ReviewUpdateRequestDto requestDto = ReviewUpdateRequestDto.builder()
                .id(nonExistReviewId)
                .content(newContent)
                .matchId(matchId)
                .build();

        // when then
        assertThatThrownBy(() -> reviewService.updateReview(requestDto))
                .isInstanceOf(NonExistReviewException.class)
                .hasMessage("존재하지 않는 댓글입니다.");
    }

    @DisplayName("댓글의 deletedAt 컬럼에 삭제를 요청한 시간을 업데이트합니다.")
    @Test
    void deleteReview() throws NonExistReviewException {
        // given
        String matchId = "KR_66990325";

        Member member = createMember("testClientId", "testPassword", "testName", "testPUUID");
        Member savedUser = memberRepository.save(member);

        Review savedReview = reviewRepository.save(createReview("testContent", matchId, savedUser));

        ReviewDeleteRequestDto deleteRequestDto = ReviewDeleteRequestDto.builder()
                .id(savedReview.getId())
                .build();

        // when
        ReviewDeleteResponseDto deleteResponseDto = reviewService.deleteReview(deleteRequestDto);

        em.flush();
        em.clear();

        Review checkReview = reviewRepository.findById(savedReview.getId()).orElseThrow(() -> new NonExistReviewException("존재하지 않는 댓글입니다."));

        // then
        assertThat(deleteResponseDto).isNotNull();
        assertThat(deleteResponseDto.getId()).isEqualTo(savedReview.getId());

        assertThat(checkReview).isNotNull();
        assertThat(checkReview.getId()).isEqualTo(savedReview.getId());
        assertThat(checkReview.getDeletedAt()).isNotNull();
    }

    @DisplayName("댓글의 deletedAt 컬럼에 삭제를 요청한 시간을 업데이트합니다.")
    @Test
    void deleteNonExistReview() {
        // given
        String matchId = "KR_66990325";

        Member member = createMember("testClientId", "testPassword", "testName", "testPUUID");
        Member savedMember = memberRepository.save(member);

        Review savedReview = reviewRepository.save(createReview("testContent", matchId, savedMember));

        Long nonExistReviewId = savedReview.getId() + 1L;

        ReviewDeleteRequestDto deleteRequestDto = ReviewDeleteRequestDto.builder()
                .id(nonExistReviewId)
                .build();

        // when then
        assertThatThrownBy(() -> reviewService.deleteReview(deleteRequestDto))
                .isInstanceOf(NonExistReviewException.class)
                .hasMessage("존재하지 않는 댓글입니다.");
    }

    private Member createMember(String clientId, String password, String name, String puuid) {
        return Member.builder()
                .clientId(clientId)
                .password(password)
                .name(name)
                .puuid(puuid)
                .build();
    }

    private Review createReview(String testContent, String matchId, Member savedMember) {
        return Review.builder()
                .member(savedMember)
                .matchId(matchId)
                .content(testContent)
                .deletedAt(null)
                .build();
    }
}
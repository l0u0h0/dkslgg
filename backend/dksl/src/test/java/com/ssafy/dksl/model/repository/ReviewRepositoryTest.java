package com.ssafy.dksl.model.repository;

import com.ssafy.dksl.config.JpaAuditingConfig;
import com.ssafy.dksl.model.entity.Member;
import com.ssafy.dksl.model.entity.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaAuditingConfig.class)
class ReviewRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @DisplayName("특정 매치 ID의 댓글들 중 deletedAt 컬럼이 null인, 즉 삭제되지 않은 댓글을 조회한다.")
    @Test
    void findByMatchIdAndDeletedAtIsNull() throws InterruptedException {
        // given
        String matchId = "KR_66990325";
        Member member = createMember("testClientId", "testPassword", "testName", "testPUUID");

        Member savedMember = memberRepository.save(member);

//        ArrayList<Review> reviews = new ArrayList<>();
        for(int i = 0; i< 40; i++) {
//            reviews.add(createReview("testContent" + i, matchId, savedMember));
            reviewRepository.save(createReview("testContent" + i, matchId, savedMember));
            Thread.sleep(1);
        }

//        reviewRepository.saveAll(reviews);

        // when
        List<Review> findReviews = reviewRepository.findByMatchIdAndDeletedAtIsNullOrderByCreatedAtDesc(matchId, PageRequest.of(2, 5));

        findReviews.stream().forEach(review -> System.out.println(review.getCreatedAt()));

        // then
        assertThat(findReviews).hasSize(5)
                .extracting("matchId", "content")
                .containsExactly(
                        tuple(matchId, "testContent29"), tuple(matchId, "testContent28"), tuple(matchId, "testContent27"),
                        tuple(matchId, "testContent26"), tuple(matchId, "testContent25")
                );

        findReviews.stream().forEach(review ->
                assertThat(review.getMember())
                .extracting("clientId", "password", "name", "puuid")
                        .contains("testClientId", "testPassword", "testName", "testPUUID"));
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
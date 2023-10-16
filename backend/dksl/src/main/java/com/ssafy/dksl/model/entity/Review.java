package com.ssafy.dksl.model.entity;

import com.ssafy.dksl.model.dto.response.ReviewSearchResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/*
 * match_id로 조회하는 경우가 대부분이기 때문에 match_id로 indexing 하도록 설계함
 */
@Entity
//@Table(name = "Review", indexes = @Index(name = "idx_match_id", columnList = "match_id"))
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends Base{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("투기장 댓글 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Comment("유저에서 댓글 리스트를 조회할 일은 비즈니스 로직 상 거의 없다고 판단했기 때문에 단방향 매핑으로 설계함")
    private Member member;

    @Column(nullable = false)
    @Comment("매치 아이디는 KR_XXXXXXXXXX 형식이다.")
    private String matchId;

    @Column(nullable = false)
    @Comment("투기장 댓글 내용이다.")
    private String content;

    @Comment("댓글이 삭제되어도 DB에서 삭제하지 않고, 삭제일을 기록한다.")
    private LocalDateTime deletedAt;


    public ReviewSearchResponseDto to() {
        return ReviewSearchResponseDto.builder()
                .id(id)
                .createdAt(super.getCreatedAt())
                .summonerName(member.getName())
                .matchId(matchId)
                .content(content)
                .build();
    }

    public void updateReview(String content){
        this.content = content;
    }

    public void deleteReview() {
        this.deletedAt = LocalDateTime.now();
    }
}

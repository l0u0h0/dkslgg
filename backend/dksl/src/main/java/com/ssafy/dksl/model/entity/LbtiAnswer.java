package com.ssafy.dksl.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "lbti_answer")
@Getter
public class LbtiAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private long id;

    @Column(name = "paragraph", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("선택 항목")
    private String paragraph;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tendency_id", nullable = false)
    @Comment("연결 타입")
    private Tendency tendency;

    @Column(name = "score", nullable = false)
    @Comment("점수")
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lbti_question_id", nullable = false)
    @Comment("연결 문항")
    private LbtiQuestion lbtiQuestion;
}

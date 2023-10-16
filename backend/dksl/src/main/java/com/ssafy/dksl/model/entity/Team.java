package com.ssafy.dksl.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Team extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("이름")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chairman_id", nullable = false)
    @Comment("소속장")
    private Member chairman;

    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("소개")
    private String description;

    @Column(name = "img", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("이미지")
    private String img;

    @Column(name = "submit_at")
    @Comment("생성일")
    private LocalDateTime submitAt;  // 팀 생성 일자 삽입

    @OneToMany(mappedBy = "team")
    private List<MemberTeam> members = new ArrayList<>();
}

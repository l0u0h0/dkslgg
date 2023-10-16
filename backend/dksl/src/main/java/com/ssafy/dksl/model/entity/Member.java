package com.ssafy.dksl.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.dksl.model.dto.MemberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private long id;

    @Column(name = "client_id", nullable = false, unique = true, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("클라이언트 ID")
    private String clientId;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("비밀번호")
    private String password;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("계정 닉네임")
    private String name;

    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("전화번호")
    private String phone;


    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("이메일")
    private String email;

    @Column(name = "puuid", nullable = false, unique = true, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("PUUID")
    private String puuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tier_id", nullable = false)
    @Comment("티어")
    private Tier tier;

    @Column(name = "profile_icon_id", nullable = false)
    @Comment("프로필 아이콘 아이디")
    private int profileIconId;

    @Column(name = "rank", nullable = false)
    @Comment("랭크")
    private int rank;

    @Column(name = "level", nullable = false)
    @Comment("레벨")
    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lbti_id")
    @Comment("롤BTI")
    private Lbti lbti;

    @OneToMany(mappedBy = "member")
    private List<MemberTeam> teams = new ArrayList<>();

    @Column(name = "deleted_at")
    @Comment("삭제일")
    private LocalDateTime deletedAt;
}

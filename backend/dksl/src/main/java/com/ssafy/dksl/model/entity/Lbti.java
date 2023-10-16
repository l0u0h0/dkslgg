package com.ssafy.dksl.model.entity;

import com.ssafy.dksl.model.dto.response.lbti.LbtiResponse;
import com.ssafy.dksl.model.dto.response.lbti.TendencyResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lbti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("이름")
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("소개")
    private String description;

    @Column(name = "champion_name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("챔피언 이름")
    private String championName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_tendency_id", nullable = false)
    @Comment("첫번째 단어")
    private Tendency firstTendency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_tendency_id", nullable = false)
    @Comment("두번째 단어")
    private Tendency secondTendency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "third_tendency_id", nullable = false)
    @Comment("세번째 단어")
    private Tendency thirdTendency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fourth_tendency_id", nullable = false)
    @Comment("네번째 단어")
    private Tendency fourthTendency;

    public LbtiResponse toLbtiResponse() {
        return LbtiResponse.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .championName(this.championName)
                .firstTendency(TendencyResponse.builder()
                        .id(this.firstTendency.getId())
                        .name(this.firstTendency.getName())
                        .initial(this.firstTendency.getInitial())
                        .build())
                .secondTendency(TendencyResponse.builder()
                        .id(this.secondTendency.getId())
                        .name(this.secondTendency.getName())
                        .initial(this.secondTendency.getInitial())
                        .build())
                .thirdTendency(TendencyResponse.builder()
                        .id(this.thirdTendency.getId())
                        .name(this.thirdTendency.getName())
                        .initial(this.thirdTendency.getInitial())
                        .build())
                .fourthTendency(TendencyResponse.builder()
                        .id(this.fourthTendency.getId())
                        .name(this.fourthTendency.getName())
                        .initial(this.fourthTendency.getInitial())
                        .build())
                .build();
    }
}

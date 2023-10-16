package com.ssafy.dksl.model.entity;

import com.ssafy.dksl.model.dto.response.lbti.LbtiAnswerResponse;
import com.ssafy.dksl.model.dto.response.lbti.LbtiQuestionResponse;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lbti_question")
@Getter
public class LbtiQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private long id;

    @Column(name = "first_paragraph", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("첫번째 문단")
    private String firstParagraph;

    @Column(name = "second_paragraph", nullable = false, columnDefinition = "VARCHAR(50) CHARACTER SET UTF8")
    @Comment("두번째 문단")
    private String secondParagraph;

    @OneToMany(mappedBy = "lbtiQuestion")
    private List<LbtiAnswer> questionList;

    public LbtiQuestionResponse toQuestionResponse() {
        List<LbtiAnswer> lbtiQuestionList = this.getQuestionList();
        List<LbtiAnswerResponse> anwerList = new ArrayList<>();

        for(LbtiAnswer ql: lbtiQuestionList) {
            anwerList.add(LbtiAnswerResponse.builder()
                    .id(ql.getId())
                    .paragraph(ql.getParagraph())
                    .build());
        }


        return LbtiQuestionResponse.builder()
                .firstParagraph(this.getFirstParagraph())
                .secondParagraph(this.getSecondParagraph())
                .answerList(anwerList)
                .build();
    }
}

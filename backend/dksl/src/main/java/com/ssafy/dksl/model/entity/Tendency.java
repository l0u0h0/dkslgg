package com.ssafy.dksl.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Table(indexes = {
        @Index(name = "idx_initial", columnList = "initial")
})
@Getter
public class Tendency {
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(12) CHARACTER SET UTF8")
    @Comment("짧은 구분자")
    private String id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("이름")
    private String name;

    @Column(name = "initial", nullable = false, unique = true)
    @Comment("첫 글자")
    private char initial;
}

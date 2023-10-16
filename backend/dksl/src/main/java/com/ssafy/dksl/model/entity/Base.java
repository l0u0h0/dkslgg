package com.ssafy.dksl.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Base {

    @CreatedDate
    @Comment("생성시간")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Comment("수정시간")
    private LocalDateTime updatedAt;
}


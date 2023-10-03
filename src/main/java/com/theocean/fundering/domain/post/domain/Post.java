package com.theocean.fundering.domain.post.domain;


import com.theocean.fundering.domain.celebrity.domain.Celebrity;
import com.theocean.fundering.domain.global.AuditingFields;
import com.theocean.fundering.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="post")
public class Post{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;


    @ManyToOne(fetch = FetchType.LAZY)
    private Celebrity celebrity;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;


    @Column
    private String thumbnail;

    @Column
    private int targetPrice;

    @Column
    private int participants;

    @Column
    @DateTimeFormat
    private LocalDateTime deadline;

    @Column
    private int postOrder;

    @Builder
    public Post(Member writer, Celebrity celebrity, String title, String content, String thumbnail){
        this.writer = writer;
        this.celebrity = celebrity;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return Objects.equals(postId, post.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}

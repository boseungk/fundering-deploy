package com.theocean.fundering.domain.news.domain;

import com.theocean.fundering.global.utils.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "news")
public class News extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long writerId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column
    private String imageUrls; //TODO: 추후 리팩토링 예정

    @Column(nullable = false)
    private boolean viewRestriction;


    @Builder
    public News(Long postId, Long writerId, String title, String content, boolean viewRestriction) {
        this.postId = postId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.viewRestriction = viewRestriction;
    }

    public void updateImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News news)) return false;
        return Objects.equals(newsId, news.newsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId);
    }
}
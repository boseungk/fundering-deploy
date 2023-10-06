package com.theocean.fundering.domain.comment.domain;


import com.theocean.fundering.global.utils.AuditingFields;
import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="comment")
public class Comment extends AuditingFields {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    // 게시물
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    // 댓글 내용
    @Column(nullable = false)
    private String content;

    // 삭제 여부
    @Column(nullable = false)
    private boolean isDeleted;

    // 대댓글 여부
    @Column(nullable = false)
    private boolean isReply;

    /*
        commentOrder는 댓글이 생성될 때 부여받는 순서이다.
        Comment에서는 부모댓글의 PK를 필드값으로 갖는 대신에 부모 댓글 순서를 필드값으로 가지고 있는다.
        만약 부모댓글이 없을 경우 자기자신의 commentOrder를 parentCommentOrder필드값으로 갖는다.
    */
    @Column
    private Long parentCommentOrder;

    @Column(nullable = false)
    private Long commentOrder;

    // 대댓글 수
    @Column(nullable = false)
    private int replyCount;

    @Builder
    public Comment(Member writer, Post post, String content, Long commentOrder) {
        this.writer = writer;
        this.post = post;
        this.content = content;
        this.commentOrder = commentOrder;
        this.isReply = false;
        this.parentCommentOrder = null;
        this.replyCount = 0;
        this.isDeleted = false;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void updateIsReply(Boolean isReply) {
        this.isReply = isReply;
    }

    public void updateparentCommentOrder(Long parentCommentOrder) {
        this.parentCommentOrder = parentCommentOrder;
    }

    public void updateCommentOrder(Long order) {
        this.commentOrder = order;
    }

    public void increaseReplyCount() {
        this.replyCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return Objects.equals(commentId, comment.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }

}

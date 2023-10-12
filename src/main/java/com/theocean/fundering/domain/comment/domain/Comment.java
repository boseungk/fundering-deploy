package com.theocean.fundering.domain.comment.domain;

import com.theocean.fundering.global.utils.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

/*
 로그인한 사용자는 댓글 작성 부분에 입력을 하여 댓글을 작성할 수 있다.
 또한 댓글 옆에 댓글 작성 버튼을 클릭하여 대댓글을 작성할 수 있다.
 대댓글에 대한 대댓글은 허용되지 않는다.
*/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment")
@SQLDelete(sql = "UPDATE comment SET is_deleted = true WHERE comment_id = ?")
public class Comment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private Long writerId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isDeleted;

    // true이면 대댓글 -> 대댓글을 작성할 수 없음.
    @Column(nullable = false)
    private boolean isReply;

    // 부모 댓글의 ID
    @Column
    private Long parentCommentId;

    @Builder
    public Comment(Long writerId, Long postId, String content, boolean isReply, Long parentCommentId) {
        this.writerId = writerId;
        this.postId = postId;
        this.content = content;
        this.isReply = isReply;
        this.parentCommentId = parentCommentId;
        this.isDeleted = false;
    }

    public void updateIsReply(Boolean isReply) {
        this.isReply = isReply;
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

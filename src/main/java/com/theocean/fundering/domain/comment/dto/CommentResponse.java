package com.theocean.fundering.domain.comment.dto;

import com.theocean.fundering.domain.comment.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;


public class CommentResponse {

    @Data
    public static class findAllDTO {
        private Long commentId;
        private Long writerId;
        private String writerName;
        private String content;
        private Long parentCommentOrder;
        private Long commentOrder;
        private int childCommentCount;
        private boolean isDeleted;
        private LocalDateTime updatedAt;

        public findAllDTO(Comment comment) {
            this.commentId = comment.getCommentId();
            this.writerId = comment.getWriter().getUserId();
            this.writerName = comment.getWriter().getNickname();
            this.content = comment.getContent();
            this.parentCommentOrder = comment.getParentCommentOrder();
            this.commentOrder = comment.getCommentOrder();
            this.childCommentCount = comment.getChildCommentCount();
            this.isDeleted = comment.isDeleted();
            this.updatedAt = comment.getModifiedAt();
        }
    }
}

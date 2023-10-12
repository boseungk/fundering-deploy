package com.theocean.fundering.domain.comment.dto;

import com.theocean.fundering.domain.comment.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


public class CommentResponse {

    // 전체 댓글 조회 DTO
    @Getter
    public static class findAllDTO {
        private final List<commentsDTO> comments;
        private final Long lastComment;
        private final boolean isLastPage;

        public findAllDTO(List<commentsDTO> comments, boolean isLastPage, Long lastComment) {
            this.comments = comments;
            this.lastComment = lastComment;
            this.isLastPage = isLastPage;
        }

        public boolean getIsLastPage() {
            return isLastPage;
        }
    }

    // findAllDTO의 내부에 들어갈 댓글 정보 DTO
    @Getter
    public static class commentsDTO {
        private final Long parentComment;
        private final Long commentId;
        private final Long writerId;
        private final String writerName;
        private final String writerProfile;
        private final String content;
        private final boolean isReply;
        private final boolean isDeleted;
        private final long createdAt;

        public commentsDTO(Comment comment, String writerName, String writerProfile) {
            this.parentComment = comment.getParentCommentId();
            this.commentId = comment.getCommentId();
            this.writerId = comment.getWriterId();
            this.writerName = writerName;
            this.writerProfile = writerProfile;
            this.content = comment.getContent();
            this.isReply = comment.getIsReply();
            this.isDeleted = comment.getIsDeleted();
            this.createdAt = toEpochSecond(comment.getCreatedAt());
        }

        private long toEpochSecond(LocalDateTime localDateTime) {
            return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
        }
        public boolean getIsDeleted() {
            return isDeleted;
        }
        public boolean getIsReply() {
            return isReply;
        }
    }
}
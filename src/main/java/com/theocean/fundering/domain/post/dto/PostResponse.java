package com.theocean.fundering.domain.post.dto;

import com.theocean.fundering.domain.celebrity.domain.Celebrity;
import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class PostResponse {


    @Getter
    @Setter
    public static class FindByPostIdDTO { // 게시글 열람 DTO
        private Long postId;
        private Member writer;
        private Celebrity celebrity;
        private String title;
        private String content;
        private String thumbnail;
        private int targetPrice;
        private LocalDateTime deadline;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public FindByPostIdDTO(Post post){
            this.postId = post.getPostId();
            this.writer = post.getWriter();
            this.celebrity = post.getCelebrity();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.thumbnail = post.getThumbnail();
            this.targetPrice = post.getTargetPrice();
            this.deadline = post.getDeadline();
            this.createdAt = post.getCreatedAt();
            this.modifiedAt = post.getModifiedAt();
        }
    }

    @Getter
    @Setter
    public static class FindByMemberIdDTO{
        private Long postId;
        private Member writer;
        private Celebrity celebrity;
        private String title;
        private String thumbnail;
        private int targetPrice;
        private LocalDateTime deadline;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    public static class FindAllDTO{
        private Long postId;
        private Member writer;
        private Celebrity celebrity;
        private String title;
        private String thumbnail;
        private int targetPrice;
        private LocalDateTime deadline;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public FindAllDTO(Post post){
            this.postId = post.getPostId();
            this.writer = post.getWriter();
            this.celebrity = post.getCelebrity();
            this.title = post.getTitle();
            this.thumbnail = post.getThumbnail();
            this.targetPrice = post.getTargetPrice();
            this.deadline = post.getDeadline();
            this.createdAt = post.getCreatedAt();
            this.modifiedAt = post.getModifiedAt();
        }
    }

}

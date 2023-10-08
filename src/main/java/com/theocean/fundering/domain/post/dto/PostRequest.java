package com.theocean.fundering.domain.post.dto;


import com.theocean.fundering.domain.celebrity.domain.Celebrity;
import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.post.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

public class PostRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class PostWriteDTO { // 게시글 작성 DTO
        private Long postId;
        private Long writerId;
        private String writer;
        private Celebrity celebrity;
        private String title;
        private String content;
        private String thumbnail;
        private int targetPrice;
        private LocalDateTime deadline;
        private LocalDateTime createdAt;

        public Post toEntity(){
            return Post.builder()
                    .writer(writer)
                    .writerId(writerId)
                    .celebrity(celebrity)
                    .title(title)
                    .content(content)
                    .thumbnail(thumbnail)
                    .targetPrice(targetPrice)
                    .deadline(deadline)
                    .build();
        }

        @Builder
        public PostWriteDTO(Long postId, Long writerId, String writer, Celebrity celebrity, String title, String content, String thumbnail, int targetPrice, LocalDateTime deadline){
            this.postId = postId;
            this.writer = writer;
            this.writerId = writerId;
            this.celebrity = celebrity;
            this.title = title;
            this.content = content;
            this.thumbnail = thumbnail;
            this.targetPrice = targetPrice;
            this.deadline = deadline;
            this.createdAt = LocalDateTime.now();
        }

    }

}

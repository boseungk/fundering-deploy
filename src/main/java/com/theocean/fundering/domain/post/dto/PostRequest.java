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
        private Member writer;
        private Celebrity celebrity;
        private String title;
        private String content;
        private String thumbnail;
        private int targetPrice;
        private LocalDateTime deadline;

        public Post toEntity(){
            return Post.builder()
                    .writer(writer)
                    .celebrity(celebrity)
                    .title(title)
                    .content(content)
                    .thumbnail(thumbnail)
                    .targetPrice(targetPrice)
                    .deadline(deadline)
                    .build();
        }

        @Builder
        public PostWriteDTO(Long postId, Member writer, Celebrity celebrity, String title, String content, String thumbnail, int targetPrice, LocalDateTime deadline){
            this.postId = postId;
            this.writer = writer;
            this.celebrity = celebrity;
            this.title = title;
            this.content = content;
            this.thumbnail = thumbnail;
            this.targetPrice = targetPrice;
            this.deadline = deadline;
        }

    }

}

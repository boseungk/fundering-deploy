package com.theocean.fundering.domain.post.dto;


import com.theocean.fundering.domain.celebrity.domain.Celebrity;
import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.post.domain.Post;
import com.theocean.fundering.domain.post.domain.constant.PostStatus;
import lombok.*;

import java.time.LocalDateTime;

public class PostRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class PostWriteDTO { // 게시글 작성 DTO
        private Long celebId;
        private String title;
        private String introduction;
        private int targetPrice;
        private LocalDateTime deadline;
        private LocalDateTime createdAt;

        public Post toEntity(Member writer, Celebrity celebrity, String thumbnail, PostStatus postStatus){
            return Post.builder()
                    .writer(writer)
                    .celebrity(celebrity)
                    .title(title)
                    .introduction(introduction)
                    .thumbnail(thumbnail)
                    .targetPrice(targetPrice)
                    .deadline(deadline)
                    .postStatus(postStatus)
                    .build();
        }

        @Builder
        public PostWriteDTO(Long celebId, String title, String introduction, int targetPrice, LocalDateTime deadline){
            this.celebId = celebId;
            this.title = title;
            this.introduction = introduction;
            this.targetPrice = targetPrice;
            this.deadline = deadline;
            this.createdAt = LocalDateTime.now();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class PostEditDTO{
        private String title;
        private String introduction;
        private int targetPrice;
        private LocalDateTime deadline;
        private LocalDateTime modifiedAt;

        public Post toEntity(){
            return Post.builder()
                    .title(title)
                    .introduction(introduction)
                    .targetPrice(targetPrice)
                    .deadline(deadline)
                    .build();
        }
        @Builder
        public PostEditDTO(String title, String introduction, int targetPrice, LocalDateTime deadline){
            this.title = title;
            this.introduction = introduction;
            this.targetPrice = targetPrice;
            this.deadline = deadline;
            this.modifiedAt = LocalDateTime.now();
        }
    }

}

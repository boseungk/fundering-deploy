package com.theocean.fundering.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentRequest {
    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;

    private Long parentCommentOrder;  // 대댓글의 경우 부모 댓글의 ID. 일반 댓글은 null.

    public CommentRequest(@NotBlank(message = "댓글 내용은 필수입니다.") String content, Long parentCommentOrder) {
        this.content = content;
        this.parentCommentOrder = parentCommentOrder;
    }


}

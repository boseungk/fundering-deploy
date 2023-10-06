package com.theocean.fundering.domain.comment.controller;

import com.theocean.fundering.domain.comment.dto.CommentRequest;
import com.theocean.fundering.domain.comment.service.CommentService;
import com.theocean.fundering.domain.comment.domain.Comment;
import com.theocean.fundering.global.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // (기능 ) 댓글 작성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> createComment(@RequestBody @Valid CommentRequest commentRequest, @PathVariable long postId) {
        // memeberId 부분은 유저 구현 이후 리팩토링 예정
        Comment comment = commentService.createComment(1L, postId, commentRequest);
        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
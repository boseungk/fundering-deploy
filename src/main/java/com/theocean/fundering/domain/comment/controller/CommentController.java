package com.theocean.fundering.domain.comment.controller;

import com.theocean.fundering.domain.comment.dto.CommentRequest;
import com.theocean.fundering.domain.comment.dto.CommentResponse;
import com.theocean.fundering.domain.comment.service.CommentService;
import com.theocean.fundering.domain.comment.domain.Comment;
import com.theocean.fundering.global.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // (기능) 댓글 작성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> createComment(@RequestBody @Valid CommentRequest commentRequest, @PathVariable long postId) {
        // memeberId 부분은 유저 구현 이후 리팩토링 예정
        Comment comment = commentService.createComment(1L, postId, commentRequest);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // (기능) 댓글 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable long postId,
                                         @RequestParam(required = false, defaultValue = "0") int pageIndex,
                                         @RequestParam(required = false, defaultValue = "3") int pageSize) {

        Map<String, Object> response = commentService.getCommentsDtoByPostId(postId, PageRequest.of(pageIndex, pageSize));

        return ResponseEntity.ok(ApiUtils.success(response));
    }
}
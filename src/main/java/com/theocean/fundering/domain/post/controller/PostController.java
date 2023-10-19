package com.theocean.fundering.domain.post.controller;


import com.amazonaws.services.s3.AmazonS3Client;
import com.theocean.fundering.domain.post.dto.PostRequest;
import com.theocean.fundering.domain.post.dto.PostResponse;
import com.theocean.fundering.domain.post.service.PostService;
import com.theocean.fundering.global.utils.ApiUtils;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<?> findAll(@RequestParam(value = "postId", required = false) Long postId){
        List<PostResponse.FindAllDTO> responseDTO = postService.findAll(postId);
        return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> findByPostId(@PathVariable Long postId){
        PostResponse.FindByPostIdDTO responseDTO = postService.findByPostId(postId);
        return ResponseEntity.ok(ApiUtils.success(responseDTO));

    }

    @PostMapping("/posts/write")
    public ResponseEntity<?> writePost(@RequestBody PostRequest.PostWriteDTO postWriteDTO, @RequestPart MultipartFile thumbnail){
        postService.writePost(postWriteDTO, thumbnail);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @PutMapping("/posts/{postId}/edit")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @RequestBody PostRequest.PostEditDTO postEditDTO, @RequestPart(required = false) MultipartFile thumbnail){
        Long editedPost = postService.editPost(postId, postEditDTO, thumbnail);
        return ResponseEntity.ok(ApiUtils.success(editedPost));
    }

    @DeleteMapping("/posts/{postId}/delete")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @GetMapping("/posts/search")
    public ResponseEntity<?> searchPost(@RequestParam(value = "postId", required = false) Long postId, @RequestParam(value = "keyword") String keyword){
        var result = postService.searchPost(postId, keyword);
        return ResponseEntity.ok(ApiUtils.success(result));
    }
}

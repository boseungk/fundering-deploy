package com.theocean.fundering.domain.post.controller;


import com.theocean.fundering.domain.post.dto.PostRequest;
import com.theocean.fundering.domain.post.dto.PostResponse;
import com.theocean.fundering.domain.post.service.PostService;
import com.theocean.fundering.global.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<?> findAll(@RequestParam(value = "postId") Long postId){
        List<PostResponse.FindAllDTO> responseDTO = postService.findAll(postId);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);

        return ResponseEntity.ok(apiResult);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> findByPostId(@PathVariable Long postId){
        PostResponse.FindByPostIdDTO responseDTO = postService.findByPostId(postId);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        
        return ResponseEntity.ok(apiResult);

    }

    @PostMapping("/posts/write")
    public ResponseEntity<?> writePost(@RequestBody PostRequest.PostWriteDTO postWriteDTO, @RequestPart MultipartFile thumbnail){
        postService.writePost(postWriteDTO);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @PutMapping("/posts/{postId}/edit")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @RequestBody PostRequest.PostEditDTO postEditDTO){
        Long editedPost = postService.editPost(postId, postEditDTO);
        return ResponseEntity.ok(ApiUtils.success(editedPost));
    }

    @DeleteMapping("/posts/{postId}/delete")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok(ApiUtils.success(null));
    }



}

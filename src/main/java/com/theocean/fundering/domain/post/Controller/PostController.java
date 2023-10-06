package com.theocean.fundering.domain.post.Controller;


import com.theocean.fundering.domain.post.dto.PostRequest;
import com.theocean.fundering.domain.post.dto.PostResponse;
import com.theocean.fundering.domain.post.service.PostService;
import com.theocean.fundering.global.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/fundings")
    public ResponseEntity<?> findAll(){

    }

    @GetMapping("/fundings/{postId}")
    public ResponseEntity<?> findByPostId(@PathVariable Long postId){
        PostResponse.FindByPostIdDTO responseDTO = postService.findByPostId(postId);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        
        return ResponseEntity.ok(apiResult);

    }

    @PostMapping("/fundings/write")
    public ResponseEntity<?> writePost(PostRequest.PostWriteDTO postWriteDTO){
        postService.writePost(postWriteDTO);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @PutMapping("/fundings/{postId}/edit")
    public ResponseEntity<?> editPost(@PathVariable Long postId, PostRequest.PostWriteDTO postWriteDTO){

    }

    @DeleteMapping("/fundings/{postId}/delete")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){

    }



}

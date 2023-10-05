package com.theocean.fundering.domain.post.Controller;


import com.theocean.fundering.domain.post.dto.PostRequest;
import com.theocean.fundering.domain.post.service.PostService;
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

    @GetMapping("/fundings/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){


    }

    @GetMapping("/fundings/write")
    public ResponseEntity<?> writePost(){

    }

    @PostMapping("/fundings/write")
    public ResponseEntity<?> writePost(PostRequest.PostWriteDTO postWriteDTO){

    }

    @PutMapping("/fundings/{id}/edit")
    public ResponseEntity<?> editPost(@PathVariable int id, PostRequest.PostWriteDTO postWriteDTO){

    }

    @DeleteMapping("/fundings/{id}/delete")
    public ResponseEntity<?> deletePost(@PathVariable int id){

    }



}

package com.theocean.fundering.domain.post.service;


import com.querydsl.core.BooleanBuilder;
import com.theocean.fundering.domain.post.domain.Post;
import com.theocean.fundering.domain.post.domain.QPost;
import com.theocean.fundering.domain.post.dto.PostRequest;
import com.theocean.fundering.domain.post.dto.PostResponse;
import com.theocean.fundering.domain.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Transactional
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public void writePost(PostRequest.PostWriteDTO postWriteDTO){
        postRepository.save(postWriteDTO.toEntity());
    }

    public PostResponse.FindByPostIdDTO findByPostId(Long postId){
        Post postPS = postRepository.findByPostId(postId).orElseThrow();
        return new PostResponse.FindByPostIdDTO(postPS);

    }


    public List<PostResponse.FindAllDTO> findAll(Long postId, int pageSize){
        return postRepository.findAll(postId, pageSize);
    }

}

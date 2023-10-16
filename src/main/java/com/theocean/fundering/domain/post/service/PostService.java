package com.theocean.fundering.domain.post.service;


import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.member.repository.MemberRepository;
import com.theocean.fundering.domain.post.domain.Post;
import com.theocean.fundering.domain.post.dto.PostRequest;
import com.theocean.fundering.domain.post.dto.PostResponse;
import com.theocean.fundering.domain.post.repository.PostRepository;
import com.theocean.fundering.global.utils.HTMLUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
//  private final CelebRepository celebRepository;
    /**
     * Member와 Celebrity 도메인의 작업이 완료되어야 구현 가능
     */
    @Transactional
    public void writePost(PostRequest.PostWriteDTO postWriteDTO){
         Member writer = memberRepository.findById(postWriteDTO.getWriterId()).orElseThrow();
        // Celebrity celebrity = celebRepository.findById(postWriteDTO.getCelebId());
         String htmlData = HTMLUtils.markdownToHTML(postWriteDTO.getContent());
         postWriteDTO.setContent(htmlData);
        // postRepository.save(postWriteDTO.toEntity(writer, celebrity));
    }

    public PostResponse.FindByPostIdDTO findByPostId(Long postId){
        Post postPS = postRepository.findByPostId(postId).orElseThrow();
        return new PostResponse.FindByPostIdDTO(postPS);

    }

    public List<PostResponse.FindAllDTO> findAll(Long postId){
        var postList = postRepository.findAll(postId);
        var checkForNext = postRepository.findAll(postList.get(postList.size() - 1).getPostId() + 1);
        if (checkForNext == null)
            postList.get(postList.size() - 1).setLast(true);
        return postList;
    }

    public List<PostResponse.FindAllDTO> findAllByWriterId(Long postId, Long writerId){
        var postList = postRepository.findAllByWriterId(postId, writerId);
        var checkForNext = postRepository.findAllByWriterId(postList.get(postList.size() - 1).getPostId() + 1, writerId);
        if (checkForNext == null)
            postList.get(postList.size() - 1).setLast(true);
        return postList;
    }
    @Transactional
    public Long editPost(Long postId, PostRequest.PostEditDTO postEditDTO){
        Post postPS = postRepository.findByPostId(postId).orElseThrow();
        String htmlData = HTMLUtils.markdownToHTML(postEditDTO.getContent());
        postEditDTO.setContent(htmlData);
        postPS.update(postEditDTO);
        return postId;
    }

    public void deletePost(Long postId){
        postRepository.deleteByPostId(postId);
    }

}

package com.theocean.fundering.domain.comment.service;

import com.theocean.fundering.domain.comment.domain.Comment;
import com.theocean.fundering.domain.comment.dto.CommentRequest;
import com.theocean.fundering.domain.comment.dto.CommentResponse;
import com.theocean.fundering.domain.comment.repository.CommentRepository;
import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.member.repository.MemberRepository;
import com.theocean.fundering.domain.post.domain.Post;
import com.theocean.fundering.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    // (기능) 댓글 작성
    @Transactional
    public Comment createComment(Long memberId, Long postId, CommentRequest.saveDTO request) {

        // 댓글 작성 이전 회원과 게시물 존재여부 확인
        Member writer = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다: " + memberId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다: " + postId));

        // 댓글 순서 구하는 로직 - 특정 post내에서 가장 최근에 작성된 댓글의 순서 + 1
        Long commentOrder = commentRepository.getLastCommentOrder(post.getPostId()) + 1;

        // RequestBody value
        Long parentCommentOrder = request.getParentCommentOrder();
        String content = request.getContent();

        // Comment 객체 기본 생성
        Comment newComment = Comment.builder()
                .writer(writer)
                .post(post)
                .content(content)
                .commentOrder(commentOrder)
                .build();

        // 대댓글 생성 로직 (부모 댓글이 존재할 경우)
        if (parentCommentOrder != null) {
            Comment parentComment = commentRepository.findByCommentOrder(parentCommentOrder)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다: " + parentCommentOrder));

            newComment.updateParentCommentOrder(parentCommentOrder);

            // 부모댓글 업데이트 (대댓글 유무 변경, 대댓글 수 증가)
            if (parentComment.isParentComment()) {
                parentComment.updateIsParentComment(false);
            }
            parentComment.increaseChildCommentCount();
        }
        else { // 일반 댓글 생성 로직 (부모 댓글이 존재하지 않을 경우)
            newComment.updateParentCommentOrder(commentOrder);
        }

        return commentRepository.save(newComment);
    }


    // (기능) 댓글 목록 조회
    public Map<String, Object> getCommentsDtoByPostId(long postId, PageRequest pageRequest) {
        Page<Comment> commentsPage = commentRepository.findByPost_PostIdOrderByParentCommentOrderAscCommentOrderAsc(postId, pageRequest);

        // Comment 객체들을 CommentResponse.findAllDTO 객체들로 변환
        List<CommentResponse.findAllDTO> responseDtos = commentsPage.getContent().stream()
                .map(CommentResponse.findAllDTO::new)
                .collect(Collectors.toList());


        boolean isLastPage = commentsPage.isLast();
        Map<String, Object> response = new HashMap<>();
        response.put("comments", responseDtos);
        response.put("isLastPage", isLastPage);

        return response;
    }

    // (기능) 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다: " + commentId));

        commentRepository.delete(comment);
    }
}

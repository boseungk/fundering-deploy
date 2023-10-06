package com.theocean.fundering.domain.comment.service;

import com.theocean.fundering.domain.comment.domain.Comment;
import com.theocean.fundering.domain.comment.dto.CommentRequest;
import com.theocean.fundering.domain.comment.repository.CommentRepository;
import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    // 댓글 생성
    @Transactional
    public Comment createComment(Long memberId, Long postId, CommentRequest request) {

        // 댓글 작성 이전 회원과 게시물 존재여부 확인
        Member writer = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다: " + memberId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다: " + postId));


        // 댓글 순서 구하는 로직 - 특정 post내에서 가장 최근에 작성된 댓글의 순서 + 1
        Long commentOrder = (commentRepository.getLastCommentOrder(post.getPostId()) + 1);


        // RequestBody value
        Long parentCommentOrder = request.getParentCommentOrder();
        String content = request.getContent();

        Comment newComment;

        // 대댓글 생성 로직 (부모 댓글이 존재할 경우)
        if (parentCommentOrder != null) {
            Comment parentComment = commentRepository.findByCommentOrder(parentCommentOrder)
                    .orElseThrow(() -> new IllegalArgumentException("Parent comment not found"));

            // 대댓글 생성
            newComment = Comment.builder()
                    .writer(writer)
                    .post(post)
                    .content(content)
                    .commentOrder(commentOrder)
                    .build();
            newComment.updateparentCommentOrder(parentCommentOrder);

            // 부모댓글 업데이트 (대댓글 유무 변경, 대댓글 수 증가)
            if (!parentComment.isReply()) {
                parentComment.updateIsReply(true);
            }
            parentComment.increaseReplyCount();


        } // 댓글 생성 로직 (부모 댓글이 존재하지 않을 경우)
        else {
            newComment = Comment.builder()
                    .writer(writer)
                    .post(post)
                    .content(content)
                    .commentOrder(commentOrder)
                    .build();
            newComment.updateparentCommentOrder(commentOrder);
        }

        return commentRepository.save(newComment);
    }

    // ... Other service methods
}

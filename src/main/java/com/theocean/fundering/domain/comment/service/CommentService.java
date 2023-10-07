package com.theocean.fundering.domain.comment.service;

import com.theocean.fundering.domain.comment.domain.Comment;
import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.comment.dto.CommentRequest;
import com.theocean.fundering.domain.comment.dto.CommentResponse;
import com.theocean.fundering.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다: " + memberId);
        }

        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("존재하지 않는 게시물입니다: " + postId);
        }

        // 댓글 순서 구하는 로직 - 특정 post내에서 가장 최근에 작성된 댓글의 순서 + 1
        Long commentOrder = commentRepository.getLastCommentOrder(postId) + 1;

        // RequestBody value
        Long parentCommentOrder = request.getParentCommentOrder();
        String content = request.getContent();

        // Comment 객체 기본 생성
        Comment newComment = Comment.builder()
                .writerId(memberId)
                .postId(postId)
                .content(content)
                .commentOrder(commentOrder)
                .build();

        // 대댓글 생성 로직 (부모 댓글이 존재할 경우)
        if (parentCommentOrder != null) {
            Comment parentComment = commentRepository.findByPostIdAndCommentOrder(postId, parentCommentOrder)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다: " + parentCommentOrder));

            newComment.updateParentCommentOrder(parentCommentOrder);

            // 부모댓글 업데이트 (대댓글 유무 변경, 대댓글 수 증가)
            if (!parentComment.isHasReply()) {
                parentComment.updatehasReply(true);
            }
            parentComment.increaseChildCommentCount();
        }
        else { // 일반 댓글 생성 로직 (부모 댓글이 존재하지 않을 경우)
            newComment.updateParentCommentOrder(commentOrder);
        }

        return commentRepository.save(newComment);
    }

    // (기능) 댓글 목록 조회 - 유저Id를 이용하여 commentsDTO를 생성하는 로직
    private CommentResponse.commentsDTO createCommentsDTO(Comment comment) {
        Member writer = memberRepository.findById(comment.getWriterId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다: " + comment.getWriterId()));

        return new CommentResponse.commentsDTO(
                comment,
                writer.getNickname(),
                "ImageURL_Example"
                //writer.getProfileImage()  // Todo: 유저 ProfileImage필드 추가 이후 리팩토링 예정
        );
    }


    // (기능) 댓글 목록 조회 - 컨트롤러로 findAllDTO 리턴
    public CommentResponse.findAllDTO getCommentsDtoByPostId(long postId, PageRequest pageRequest) {
        Page<Comment> commentsPage = commentRepository.findByPostIdOrdered(postId, pageRequest);


        List<CommentResponse.commentsDTO> commentsDtos = commentsPage.getContent().stream()
                .map(this::createCommentsDTO)
                .collect(Collectors.toList());

        return new CommentResponse.findAllDTO(commentsDtos, commentsPage.isLast());
    }


    // (기능) 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다: " + commentId));

        commentRepository.delete(comment);
    }
}

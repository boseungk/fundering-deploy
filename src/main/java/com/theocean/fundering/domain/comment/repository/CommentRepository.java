package com.theocean.fundering.domain.comment.repository;

import com.theocean.fundering.domain.comment.domain.Comment;
import com.theocean.fundering.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT COALESCE(MAX(c.commentOrder), 0) FROM Comment c WHERE c.post.postId = :postId")
    Long getLastCommentOrder(@Param("postId") Long postId);

    Optional<Comment> findByPostAndCommentOrder(Post post, Long commentOrder);

    Page<Comment> findByPost_PostIdOrderByParentCommentOrderAscCommentOrderAsc(Long postId, Pageable pageable);
}

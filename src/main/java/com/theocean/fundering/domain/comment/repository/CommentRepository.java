package com.theocean.fundering.domain.comment.repository;

import com.theocean.fundering.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT MAX(c.commentOrder) FROM Comment c WHERE c.post.postId = :postId")
    Long getLastCommentOrder(@Param("postId") Long postId);

    Optional<Comment> findByCommentOrder(Long parentCommentOrder);

}

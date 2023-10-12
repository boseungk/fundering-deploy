package com.theocean.fundering.domain.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.theocean.fundering.domain.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.theocean.fundering.domain.comment.domain.QComment.comment;

@RequiredArgsConstructor
@Repository
public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    private final JPAQueryFactory queryFactory;

    public List<Comment> findCommentsByPostId(Long postId, Long lastCommentId, int pageSize) {
        return queryFactory.selectFrom(comment)
                .where(comment.postId.eq(postId)
                        .and(comment.commentId.gt(lastCommentId)))
                .orderBy(
                        comment.parentCommentId.asc().nullsFirst(),
                        comment.createdAt.asc()
                )
                .limit(pageSize)
                .fetch();
    }
}

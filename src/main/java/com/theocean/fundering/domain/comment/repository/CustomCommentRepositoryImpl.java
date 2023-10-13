package com.theocean.fundering.domain.comment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
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

    public List<Comment> getCommentList(Long postId, int lastGroup, int lastOrder, int pageSize) {
        BooleanExpression whereCondition = comment.postId.eq(postId)
                .andAnyOf(
                        comment.group.goe(lastGroup).and(comment.order.gt(lastOrder)),
                        comment.group.gt(lastGroup)
                );

        return queryFactory.selectFrom(comment)
                .where(whereCondition)
                .orderBy(comment.group.asc(), comment.order.asc())
                .limit(pageSize)
                .fetch();
    }
}

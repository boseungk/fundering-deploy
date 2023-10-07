package com.theocean.fundering.domain.comment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -806805252L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final com.theocean.fundering.global.utils.QAuditingFields _super = new com.theocean.fundering.global.utils.QAuditingFields(this);

    public final NumberPath<Long> commentId = createNumber("commentId", Long.class);

    public final NumberPath<Integer> commentOrder = createNumber("commentOrder", Integer.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isReply = createBoolean("isReply");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> parentComment = createNumber("parentComment", Long.class);

    public final com.theocean.fundering.domain.post.domain.QPost post;

    public final NumberPath<Integer> replyCount = createNumber("replyCount", Integer.class);

    public final com.theocean.fundering.domain.member.domain.QMember writer;

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new com.theocean.fundering.domain.post.domain.QPost(forProperty("post"), inits.get("post")) : null;
        this.writer = inits.isInitialized("writer") ? new com.theocean.fundering.domain.member.domain.QMember(forProperty("writer")) : null;
    }

}


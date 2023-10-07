package com.theocean.fundering.domain.celebrity.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCelebrity is a Querydsl query type for Celebrity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCelebrity extends EntityPathBase<Celebrity> {

    private static final long serialVersionUID = -1856329668L;

    public static final QCelebrity celebrity = new QCelebrity("celebrity");

    public final com.theocean.fundering.global.utils.QAuditingFields _super = new com.theocean.fundering.global.utils.QAuditingFields(this);

    public final EnumPath<com.theocean.fundering.celebrity.domain.constant.CelebGender> celebGender = createEnum("celebGender", com.theocean.fundering.celebrity.domain.constant.CelebGender.class);

    public final StringPath celebGroup = createString("celebGroup");

    public final NumberPath<Long> celebId = createNumber("celebId", Long.class);

    public final StringPath celebName = createString("celebName");

    public final EnumPath<com.theocean.fundering.celebrity.domain.constant.CelebType> celebType = createEnum("celebType", com.theocean.fundering.celebrity.domain.constant.CelebType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<com.theocean.fundering.domain.follow.domain.Follow, com.theocean.fundering.domain.follow.domain.QFollow> followers = this.<com.theocean.fundering.domain.follow.domain.Follow, com.theocean.fundering.domain.follow.domain.QFollow>createList("followers", com.theocean.fundering.domain.follow.domain.Follow.class, com.theocean.fundering.domain.follow.domain.QFollow.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<com.theocean.fundering.domain.post.domain.Post, com.theocean.fundering.domain.post.domain.QPost> post = this.<com.theocean.fundering.domain.post.domain.Post, com.theocean.fundering.domain.post.domain.QPost>createList("post", com.theocean.fundering.domain.post.domain.Post.class, com.theocean.fundering.domain.post.domain.QPost.class, PathInits.DIRECT2);

    public final StringPath profileImage = createString("profileImage");

    public QCelebrity(String variable) {
        super(Celebrity.class, forVariable(variable));
    }

    public QCelebrity(Path<? extends Celebrity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCelebrity(PathMetadata metadata) {
        super(Celebrity.class, metadata);
    }

}


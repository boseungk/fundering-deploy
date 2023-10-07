package com.theocean.fundering.domain.like.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHeart is a Querydsl query type for Heart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHeart extends EntityPathBase<Heart> {

    private static final long serialVersionUID = -760724997L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHeart heart = new QHeart("heart");

    public final com.theocean.fundering.domain.celebrity.domain.QCelebrity celeb;

    public final NumberPath<Long> heartId = createNumber("heartId", Long.class);

    public final com.theocean.fundering.domain.member.domain.QMember member;

    public QHeart(String variable) {
        this(Heart.class, forVariable(variable), INITS);
    }

    public QHeart(Path<? extends Heart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHeart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHeart(PathMetadata metadata, PathInits inits) {
        this(Heart.class, metadata, inits);
    }

    public QHeart(Class<? extends Heart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.celeb = inits.isInitialized("celeb") ? new com.theocean.fundering.domain.celebrity.domain.QCelebrity(forProperty("celeb")) : null;
        this.member = inits.isInitialized("member") ? new com.theocean.fundering.domain.member.domain.QMember(forProperty("member")) : null;
    }

}


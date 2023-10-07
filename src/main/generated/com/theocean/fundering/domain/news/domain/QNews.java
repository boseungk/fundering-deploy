package com.theocean.fundering.domain.news.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNews is a Querydsl query type for News
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNews extends EntityPathBase<News> {

    private static final long serialVersionUID = 761171898L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNews news = new QNews("news");

    public final com.theocean.fundering.global.utils.QAuditingFields _super = new com.theocean.fundering.global.utils.QAuditingFields(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.theocean.fundering.domain.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> newsId = createNumber("newsId", Long.class);

    public final NumberPath<Integer> newsOrder = createNumber("newsOrder", Integer.class);

    public final com.theocean.fundering.domain.post.domain.QPost post;

    public final StringPath title = createString("title");

    public QNews(String variable) {
        this(News.class, forVariable(variable), INITS);
    }

    public QNews(Path<? extends News> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNews(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNews(PathMetadata metadata, PathInits inits) {
        this(News.class, metadata, inits);
    }

    public QNews(Class<? extends News> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.theocean.fundering.domain.member.domain.QMember(forProperty("member")) : null;
        this.post = inits.isInitialized("post") ? new com.theocean.fundering.domain.post.domain.QPost(forProperty("post"), inits.get("post")) : null;
    }

}


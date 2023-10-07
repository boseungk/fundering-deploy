package com.theocean.fundering.domain.evidence.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvidence is a Querydsl query type for Evidence
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvidence extends EntityPathBase<Evidence> {

    private static final long serialVersionUID = 1115907906L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEvidence evidence = new QEvidence("evidence");

    public final com.theocean.fundering.global.utils.QAuditingFields _super = new com.theocean.fundering.global.utils.QAuditingFields(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> evidenceId = createNumber("evidenceId", Long.class);

    public final com.theocean.fundering.domain.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.theocean.fundering.domain.post.domain.QPost post;

    public final com.theocean.fundering.domain.withdrawal.domain.QWithdrawal withdrawal;

    public QEvidence(String variable) {
        this(Evidence.class, forVariable(variable), INITS);
    }

    public QEvidence(Path<? extends Evidence> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEvidence(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEvidence(PathMetadata metadata, PathInits inits) {
        this(Evidence.class, metadata, inits);
    }

    public QEvidence(Class<? extends Evidence> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.theocean.fundering.domain.member.domain.QMember(forProperty("member")) : null;
        this.post = inits.isInitialized("post") ? new com.theocean.fundering.domain.post.domain.QPost(forProperty("post"), inits.get("post")) : null;
        this.withdrawal = inits.isInitialized("withdrawal") ? new com.theocean.fundering.domain.withdrawal.domain.QWithdrawal(forProperty("withdrawal"), inits.get("withdrawal")) : null;
    }

}


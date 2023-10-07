package com.theocean.fundering.domain.withdrawal.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWithdrawal is a Querydsl query type for Withdrawal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWithdrawal extends EntityPathBase<Withdrawal> {

    private static final long serialVersionUID = 618216382L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWithdrawal withdrawal = new QWithdrawal("withdrawal");

    public final com.theocean.fundering.global.utils.QAuditingFields _super = new com.theocean.fundering.global.utils.QAuditingFields(this);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath depositAccount = createString("depositAccount");

    public final BooleanPath isApproved = createBoolean("isApproved");

    public final com.theocean.fundering.domain.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.theocean.fundering.domain.post.domain.QPost post;

    public final StringPath usage = createString("usage");

    public final NumberPath<Long> withdrawal_id = createNumber("withdrawal_id", Long.class);

    public final NumberPath<Integer> withdrawalAmount = createNumber("withdrawalAmount", Integer.class);

    public QWithdrawal(String variable) {
        this(Withdrawal.class, forVariable(variable), INITS);
    }

    public QWithdrawal(Path<? extends Withdrawal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWithdrawal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWithdrawal(PathMetadata metadata, PathInits inits) {
        this(Withdrawal.class, metadata, inits);
    }

    public QWithdrawal(Class<? extends Withdrawal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.theocean.fundering.domain.member.domain.QMember(forProperty("member")) : null;
        this.post = inits.isInitialized("post") ? new com.theocean.fundering.domain.post.domain.QPost(forProperty("post"), inits.get("post")) : null;
    }

}


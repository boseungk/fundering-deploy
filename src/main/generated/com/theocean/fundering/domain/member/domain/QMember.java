package com.theocean.fundering.domain.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -984577208L;

    public static final QMember member = new QMember("member1");

    public final com.theocean.fundering.global.utils.QAuditingFields _super = new com.theocean.fundering.global.utils.QAuditingFields(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final EnumPath<com.theocean.fundering.domain.member.domain.constant.UserRole> userRole = createEnum("userRole", com.theocean.fundering.domain.member.domain.constant.UserRole.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}


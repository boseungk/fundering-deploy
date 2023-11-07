package com.theocean.fundering.domain.celebrity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "follow",
        uniqueConstraints = @UniqueConstraint(columnNames = {"celebrity_id", "member_id"}))
@IdClass(Follow.PK.class)
@Entity
public class Follow {
    @Id
    @Column(name="celebrity_id", insertable = false, updatable = false)
    private Long celebId;

    @Id
    @Column(name = "member_id", insertable = false, updatable = false)
    private Long memberId;

    //Follow 관계의 유일성을 위한 복합키 설정
    public static class PK implements Serializable {
        Long celebId;
        Long memberId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Follow follow)) return false;
        return Objects.equals(celebId, follow.celebId) && Objects.equals(memberId, follow.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(celebId, memberId);
    }
}

package com.theocean.fundering.domain.member.domain;

import com.theocean.fundering.global.utils.AuditingFields;
import com.theocean.fundering.domain.member.domain.constant.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member",
    indexes = @Index(columnList = "email", unique = true)
)
@Entity
public class Member extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 15)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    private String refreshToken; // 리프레시 토큰

    public void changeNickname(String nickname){
        this.nickname = nickname;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    @Builder
    public Member(Long id, String nickname, String password, String email, UserRole userRole) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

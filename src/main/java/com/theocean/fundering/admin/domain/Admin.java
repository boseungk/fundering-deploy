package com.theocean.fundering.admin.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import com.theocean.fundering.user.domain.User;
import com.theocean.fundering.post.domain.Post;
import com.theocean.fundering.account.domain.Account;

@Entity
@Table(name = "Admin")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId")
    private Long id; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user; // 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post; // 게시물

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account; // 계좌

    // 생성자
    @Builder
    public Admin(User user, Post post, Account account) {
        this.user = user;
        this.post = post;
        this.account = account;
    }

    // Setter methods

}

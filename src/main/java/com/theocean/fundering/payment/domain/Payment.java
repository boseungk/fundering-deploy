package com.theocean.fundering.payment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.theocean.fundering.user.domain.User;
import com.theocean.fundering.post.domain.Post;

@Entity
@Table(name = "Payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    // 결제금액
    @Column(name = "amount")
    private Double amount;

    // 결제일시
    @Column(name = "paymentDate")
    private LocalDateTime paymentDate;

    // 생성자
    @Builder
    public Payment(User user, Post post, Double amount, LocalDateTime paymentDate) {
        this.user = user;
        this.post = post;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    // Setter methods

}

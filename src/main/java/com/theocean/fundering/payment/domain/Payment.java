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

    @Column(name = "amount")
    private Double amount;

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
    public void updateUser(User user) {
        this.user = user;
    }

    public void updatePost(Post post) {
        this.post = post;
    }

    public void updateAmount(Double amount) {
        this.amount = amount;
    }

    public void updatePaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}

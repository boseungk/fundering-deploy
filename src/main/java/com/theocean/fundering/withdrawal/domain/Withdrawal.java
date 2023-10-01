package com.theocean.fundering.withdrawal.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import com.theocean.fundering.user.domain.User;
import com.theocean.fundering.post.domain.Post;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "Withdrawal")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "withdrawalId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    // 사용처
    @Column(name = "usage", nullable = false)
    private String usage;

    // 출금계좌
    @Column(name = "depositAccount", nullable = false)
    private String depositAccount;

    // 출금액
    @Column(name = "withdrawalAmount", nullable = false)
    private Double withdrawalAmount;

    // 신청일자
    @CreatedDate
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    // 승인 여부
    @Column(name = "isApproved", nullable = false)
    private Boolean isApproved;

    // 승인 일자
    @Column(name = "approvalDate")
    private LocalDateTime approvalDate;

    // 생성자
    @Builder
    public Withdrawal(User user, Post post, String usage, String depositAccount,
                      Double withdrawalAmount, Boolean isApproved, LocalDateTime approvalDate) {
        this.user = user;
        this.post = post;
        this.usage = usage;
        this.depositAccount = depositAccount;
        this.withdrawalAmount = withdrawalAmount;
        this.isApproved = isApproved;
        this.approvalDate = approvalDate;
    }

    // Setter Methods
    public void updateUsage(String usage) {
        this.usage = usage;
    }

    public void updateDepositAccount(String depositAccount) {
        this.depositAccount = depositAccount;
    }

    public void updateWithdrawalAmount(Double withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public void updateIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public void updateApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Withdrawal that = (Withdrawal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

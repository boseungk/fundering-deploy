package com.theocean.fundering.withdrawal.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.theocean.fundering.user.domain.User;
import com.theocean.fundering.post.domain.Post;

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
    @Column(name = "usage")
    private String usage;

    // 출금계좌
    @Column(name = "depositAccount")
    private String depositAccount;

    // 출금액
    @Column(name = "withdrawalAmount")
    private Double withdrawalAmount;

    // 신청일자
    @Column(name = "applicationDate")
    private LocalDateTime applicationDate;

    // 승인 여부
    @Column(name = "isApproved")
    private Boolean isApproved;

    // 승인 일자
    @Column(name = "approvalDate", nullable = true)
    private LocalDateTime approvalDate;

    // 생성자
    @Builder
    public Withdrawal(User user, Post post, String usage, String depositAccount,
                      Double withdrawalAmount, LocalDateTime applicationDate,
                      Boolean isApproved, LocalDateTime approvalDate) {
        this.user = user;
        this.post = post;
        this.usage = usage;
        this.depositAccount = depositAccount;
        this.withdrawalAmount = withdrawalAmount;
        this.applicationDate = applicationDate;
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

    public void updateApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void updateIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public void updateApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }
}

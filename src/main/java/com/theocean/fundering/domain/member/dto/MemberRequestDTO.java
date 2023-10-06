package com.theocean.fundering.domain.member.dto;

import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.member.domain.constant.UserRole;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberRequestDTO {
    private String email;
    private String nickname;
    private String password;

    public static MemberRequestDTO from(Member member){
        return MemberRequestDTO.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .build();
    }
    public Member getEntity(){
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .userRole(UserRole.USER)
                .build();
    }
    public void encodePassword(String encodePassword){
        this.password = encodePassword;
    }

}

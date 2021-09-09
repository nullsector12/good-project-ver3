package com.ksy.tom.ver3.goodprojectver3.web.dto;

import com.ksy.tom.ver3.goodprojectver3.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterMemberRequestDTO {

    private String account;
    private String password;
    private String name;
    private String email;


    @Builder
    public RegisterMemberRequestDTO(String account, String password, String name, String email) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;

    }

    public Member toEntity() {
        return Member.builder()
                .account(account)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }
}

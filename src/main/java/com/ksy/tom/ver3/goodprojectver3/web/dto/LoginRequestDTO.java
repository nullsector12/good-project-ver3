package com.ksy.tom.ver3.goodprojectver3.web.dto;

import com.ksy.tom.ver3.goodprojectver3.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDTO {

    private String account;
    private String password;

    @Builder
    public LoginRequestDTO(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .account(account)
                .password(password)
                .build();
    }
}

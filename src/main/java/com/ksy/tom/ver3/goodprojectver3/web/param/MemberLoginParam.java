package com.ksy.tom.ver3.goodprojectver3.web.param;

import com.ksy.tom.ver3.goodprojectver3.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberLoginParam {

    private String account;
    private String password;

    @Builder
    public MemberLoginParam(String account, String password) {
        this.account = account;
        this.password = password;
    }
}

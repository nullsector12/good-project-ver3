package com.ksy.tom.ver3.goodprojectver3.web.result;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginFrontDTO {

    private String name;
    private String email;

    @Builder
    public LoginFrontDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

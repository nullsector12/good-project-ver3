package com.ksy.tom.ver3.goodprojectver3.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 11, nullable = false)
    private String account;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(length = 10, nullable = false)
    private String name;
    @Column(length = 30)
    private String email;

    @Builder
    public Member(String account, String password, String name, String email) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
    }

}

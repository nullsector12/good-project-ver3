package com.ksy.tom.ver3.goodprojectver3.service;

import com.ksy.tom.ver3.goodprojectver3.domain.member.Member;
import com.ksy.tom.ver3.goodprojectver3.domain.member.MemberRepository;
import com.ksy.tom.ver3.goodprojectver3.web.dto.LoginRequestDTO;
import com.ksy.tom.ver3.goodprojectver3.web.dto.LoginResponseDTO;
import com.ksy.tom.ver3.goodprojectver3.web.dto.RegisterMemberRequestDTO;
import com.ksy.tom.ver3.goodprojectver3.web.param.MemberLoginParam;
import com.ksy.tom.ver3.goodprojectver3.web.result.LoginFrontDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long registerMember(RegisterMemberRequestDTO registerMemberDTO) {

        return memberRepository.save(registerMemberDTO.toEntity()).getSeq();
    }

    public LoginFrontDTO loginMember(MemberLoginParam param) {
        Member member = login(param);

        if(member != null) {
            LoginFrontDTO frontDTO = LoginFrontDTO.builder()
                    .name(member.getName())
                    .email(member.getEmail())
                    .build();

            return frontDTO;
        }
        return null;
    }

    public Member login(MemberLoginParam param) {
        return memberRepository.login(param.getAccount(), param.getPassword());
    }
}

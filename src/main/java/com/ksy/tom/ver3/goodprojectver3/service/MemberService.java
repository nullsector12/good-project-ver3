package com.ksy.tom.ver3.goodprojectver3.service;

import com.ksy.tom.ver3.goodprojectver3.domain.member.MemberRepository;
import com.ksy.tom.ver3.goodprojectver3.web.dto.RegisterMemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long registerMember(RegisterMemberRequestDTO registerMemberDTO) {

        return memberRepository.save(registerMemberDTO.toEntity()).getSeq();
    }
}

package com.ksy.tom.ver3.goodprojectver3.web;

import com.ksy.tom.ver3.goodprojectver3.service.MemberService;
import com.ksy.tom.ver3.goodprojectver3.web.dto.RegisterMemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/tom_api/v1/member/register")
    public Long registerMember(@RequestBody RegisterMemberRequestDTO registerMemberDTO) {
        return memberService.registerMember(registerMemberDTO);
    }

}

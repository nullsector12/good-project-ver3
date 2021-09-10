package com.ksy.tom.ver3.goodprojectver3.web;

import com.ksy.tom.ver3.goodprojectver3.service.MemberService;
import com.ksy.tom.ver3.goodprojectver3.web.dto.LoginResponseDTO;
import com.ksy.tom.ver3.goodprojectver3.web.dto.RegisterMemberRequestDTO;
import com.ksy.tom.ver3.goodprojectver3.web.param.MemberLoginParam;
import com.ksy.tom.ver3.goodprojectver3.web.result.LoginFrontDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tom_api/v1/member")
public class MemberApiController {

    @Resource
    private LoginResponseDTO loginResponseDTO;

    private final MemberService memberService;

    @PostMapping("/login")
    public LoginFrontDTO getLogin (@RequestBody MemberLoginParam param) {

        LoginFrontDTO frontDTO = memberService.loginMember(param);

        loginResponseDTO.setName(frontDTO.getName());
        loginResponseDTO.setEmail(frontDTO.getEmail());
        return frontDTO;
    }

    @GetMapping("/login_member_session")
    public String getSessionInfo() {
        return loginResponseDTO.toString();
    }


    @PostMapping("/register")
    public Long registerMember(@RequestBody RegisterMemberRequestDTO registerMemberDTO) {
        return memberService.registerMember(registerMemberDTO);
    }

}

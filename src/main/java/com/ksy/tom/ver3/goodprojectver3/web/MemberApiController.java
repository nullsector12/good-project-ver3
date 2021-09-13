package com.ksy.tom.ver3.goodprojectver3.web;

import com.ksy.tom.ver3.goodprojectver3.service.MemberService;
import com.ksy.tom.ver3.goodprojectver3.web.dto.LoginResponseDTO;
import com.ksy.tom.ver3.goodprojectver3.web.dto.RegisterMemberRequestDTO;
import com.ksy.tom.ver3.goodprojectver3.web.param.MemberLoginParam;
import com.ksy.tom.ver3.goodprojectver3.web.result.LoginFrontDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tom_api/v1/member")
public class MemberApiController {

    @Resource
    private LoginResponseDTO loginResponseDTO;

    private final MemberService memberService;

    @PostMapping("/login")
    public LoginFrontDTO getLogin (@RequestBody MemberLoginParam param, HttpSession session, Model model) {

        LoginFrontDTO frontDTO = memberService.loginMember(param);

        session.setAttribute("user", frontDTO);
        model.addAttribute("name", frontDTO.getName());
        model.addAttribute("email", frontDTO.getEmail());
        return frontDTO;
    }

    @GetMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("user");

        return "redirect:/";
    }


    @PostMapping("/register")
    public Long registerMember(@RequestBody RegisterMemberRequestDTO registerMemberDTO) {
        return memberService.registerMember(registerMemberDTO);
    }

}

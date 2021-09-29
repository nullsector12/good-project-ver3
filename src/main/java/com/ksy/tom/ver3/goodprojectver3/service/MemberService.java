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
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long registerMember(RegisterMemberRequestDTO registerMemberDTO) {

        return memberRepository.save(registerMemberDTO.toEntity()).getSeq();
    }

    public LoginFrontDTO loginMember(MemberLoginParam param, HttpServletRequest request, HttpServletResponse response) {

        // https://jeong-pro.tistory.com/80 - Session / Cookie
        // https://www.youtube.com/watch?v=LNTscUEgcnU - Application, Session, Cookie
        // https://ifuwanna.tistory.com/223 - cookie same-site

        // 어플리케이션 전역에서 사용가능
        // WAS 서버 메모리에 저장
        // WAS가 시작해서 종료할 때 까지
        ServletContext application = request.getServletContext();

        // 세션 범주내에서 사용가능
        // 세션이 시작해서 종료할 때 까지
        // WAS 서버 메모리에 저장
        // 사용자 개념
        // 웹 브라우저별로 각각 다른 세션을 생성
        HttpSession session = request.getSession();

        // 웹 브라우저 별로 지정한 path 공간 범주
        // 브라우저에 전달한 시간부터 만료기간까지
        // 웹 브라우저의 파일 또는 메모리
        // 긴 시간 보관하거나 자주 사용하지 않는 요청에 관한 정보는 쿠키를 사용하는 것이 좋다.
        Cookie[] cookies = request.getCookies();

//            if(application.getAttribute("name") != null) {
//        if(session.getAttribute("name") != null) {
        if(cookies.length > 0) {
            String name = "";
            String email = "";
            for(Cookie c : cookies) {
                if (c.getName().equals("name")) {
                    name = c.getValue();
                } else {
                    email = c.getValue();
                }
            }

            LoginFrontDTO frontDTO = LoginFrontDTO.builder()
//                    .name(application.getAttribute("name").toString())
//                    .email(application.getAttribute("email").toString())
//                    .name(session.getAttribute("name").toString())
//                    .email(session.getAttribute("email").toString())
                    .name(name)
                    .email(email)
                    .build();

//            application.setAttribute("name", frontDTO.getName());
//            application.setAttribute("email", frontDTO.getEmail());
//            session.setAttribute("name", frontDTO.getName());
//            session.setAttribute("email", frontDTO.getEmail());

            return frontDTO;
        } else {
            Member member = login(param);
            LoginFrontDTO frontDTO = LoginFrontDTO.builder()
                    .name(member.getName())
                    .email(member.getEmail())
                    .build();

            Cookie nameCookie = new Cookie("name", frontDTO.getName());
            Cookie emailCookie = new Cookie("email", frontDTO.getEmail());
            response.addCookie(nameCookie);
            response.addCookie(emailCookie);
            return frontDTO;
        }
    }

    public Member login(MemberLoginParam param) {
        return memberRepository.login(param.getAccount(), param.getPassword());
    }
}

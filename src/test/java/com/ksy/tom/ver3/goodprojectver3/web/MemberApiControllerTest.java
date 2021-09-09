package com.ksy.tom.ver3.goodprojectver3.web;

import com.ksy.tom.ver3.goodprojectver3.domain.member.Member;
import com.ksy.tom.ver3.goodprojectver3.domain.member.MemberRepository;
import com.ksy.tom.ver3.goodprojectver3.web.dto.RegisterMemberRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void tearDown() throws Exception {
        memberRepository.deleteAll();
    }

    @Test
    public void Member_등록_확인 () {
        RegisterMemberRequestDTO requestDTO = RegisterMemberRequestDTO.builder()
                .account("tom1234")
                .password("test1234")
                .name("Tom")
                .email("Tom1234@naver.com")
                .build();


        String url = "http://localhost:" + port + "/tom_api/v1/member/register";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDTO, Long.class );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> allMembers = memberRepository.findAll();
        assertThat(allMembers.get(0).getName()).isEqualTo(requestDTO.getName());
        assertThat(allMembers.get(0).getAccount()).isEqualTo(requestDTO.getAccount());
    }


}

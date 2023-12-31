package com.example.springsessionproject;

import com.example.springsessionproject.domain.member.Member;
import com.example.springsessionproject.domain.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("tester");
        member.setPassword("testpw");

        memberRepository.save(member);
    }
}

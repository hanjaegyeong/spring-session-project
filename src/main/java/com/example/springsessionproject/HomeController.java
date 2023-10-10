package com.example.springsessionproject;

import com.example.springsessionproject.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {
    // @SessionAttribute 이용
    // value 받아오는 과정 따로 하지 않고, 애노테이션에서 바로 세션값 받아옴
    @GetMapping("/home")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {

        //세션에 회원 데이터가 없으면 home
        if (member == null) {
            return "home";
        }

        // 세션이 유지되면 loginHome으로 이동
        model.addAttribute("member", member);
        return "loginHome";
    }
}
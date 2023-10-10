package com.example.springsessionproject.web.member;

import com.example.springsessionproject.domain.member.Member;
import com.example.springsessionproject.domain.member.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/signup")
    public String addForm(@ModelAttribute("member") Member member) {
        return "signup";
    }

    @PostMapping("/signup")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        memberRepository.save(member);
        return "redirect:/home";
    }
}

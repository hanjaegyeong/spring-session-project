package com.example.springsessionproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    //username, password
    @PostMapping("/signup")
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("username={}, password={}", username, password);
        response.getWriter().write("ok");
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
}
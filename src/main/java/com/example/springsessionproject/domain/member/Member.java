package com.example.springsessionproject.domain.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Member implements Serializable {
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    public Member(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public Member() {

    }
}


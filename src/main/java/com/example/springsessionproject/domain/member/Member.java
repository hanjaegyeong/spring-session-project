package com.example.springsessionproject.domain.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {
    private Long id;

    @NotEmpty
    private String loginId; //로그인id
    @NotEmpty
    private String password;
}

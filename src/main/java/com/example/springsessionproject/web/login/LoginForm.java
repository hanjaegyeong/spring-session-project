package com.example.springsessionproject.web.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    public LoginForm(String loginId, String password) {
        this.loginId =  loginId;
        this.password = password;
    }
}

package com.sparta.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private final String username;
    private final String password;

    public SignupRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

package com.sparta.post.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final String message;
    private final Integer statusCode;

    public LoginResponseDto(String username, Integer statusCode) {
        this.message = username;
        this.statusCode = statusCode;
    }
}

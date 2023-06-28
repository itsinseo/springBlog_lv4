package com.sparta.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private final String message;
    private final Integer statusCode;

    public LoginResponseDto(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}

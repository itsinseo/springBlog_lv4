package com.sparta.springBlog.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private final String message;
    private final Integer statusCode;

    public SignupResponseDto(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}

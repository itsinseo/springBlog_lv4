package com.sparta.springBlog.dto;

import lombok.Getter;

@Getter
public class ApiResponseDto {
    // 회원가입, 로그인, 게시글 삭제 Response 용 DTO

    private final String message;
    private final Integer statusCode;

    public ApiResponseDto(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}

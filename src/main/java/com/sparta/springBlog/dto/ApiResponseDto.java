package com.sparta.springBlog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto {
    // 회원가입, 로그인, 게시글 삭제 Response 용 DTO

    private String message;
    private Integer statusCode;

    public ApiResponseDto(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}

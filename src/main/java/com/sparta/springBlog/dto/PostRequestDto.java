package com.sparta.springBlog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String postName;
    private String userName;
    private String postContent;
    private String password;
}

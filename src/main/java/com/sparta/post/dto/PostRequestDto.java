package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String postName;
    private String userName;
    private String postContent;
}

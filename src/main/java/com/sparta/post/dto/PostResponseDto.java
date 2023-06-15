package com.sparta.post.dto;

import com.sparta.post.entity.Post;

public class PostResponseDto {
    private Long id;
    private String postName;
    private String userName;
    private String postContent;

    public PostResponseDto(Post post) {
//        this.id = post.getId();
        this.postName = post.getPostName();
        this.userName = post.getUserName();
        this.postContent = post.getPostContent();
    }
}

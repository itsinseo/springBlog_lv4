package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private final Long id;
    private final String postName;
    private final String userName;
    private final String postContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.postName = post.getPostName();
        this.userName = post.getUserName();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

//    public static class PostResponseSuccessDto{
//        private Long id;
//        private String postName;
//        private String userName;
//        private String postContent;
//
//    }
//
//    public static class PostResponseFailDto{
//        private boolean success;
//
//    }
}

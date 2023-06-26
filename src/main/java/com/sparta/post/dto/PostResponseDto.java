package com.sparta.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {
    private Long id;
    private String postName;
    private String userName;
    private String postContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean success;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.postName = post.getPostName();
        this.userName = post.getUserName();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    // DELETE API Response 용도의 생성자
    public PostResponseDto(Boolean success) {
        this.success = success;
    }
}

package com.sparta.springBlog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.springBlog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {
    private final Long id;
    private final String postName;
    private final String userName;
    private final String postContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<CommentResponseDto> commentList;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.postName = post.getPostName();
        this.userName = post.getUserName();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = post.getCommentList().stream().map(CommentResponseDto::new)
                .sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed())
                .toList();
    }
}

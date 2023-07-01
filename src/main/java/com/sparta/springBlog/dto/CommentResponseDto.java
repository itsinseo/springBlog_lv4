package com.sparta.springBlog.dto;

import com.sparta.springBlog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long comment_id;
    private final Long post_id;
    private final String userName;
    private final String commentContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.comment_id = comment.getId();
        this.post_id = comment.getPost().getId();
        this.userName = comment.getUser().getUsername();
        this.commentContent = comment.getCommentContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}

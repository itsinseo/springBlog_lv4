package com.sparta.springBlog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.springBlog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDto extends ApiResponseDto {
    private final Long comment_id;
    private final Long post_id;
    private final String userName;
    private final String commentContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Integer likes;

    public CommentResponseDto(Comment comment) {
        this.comment_id = comment.getId();
        this.post_id = comment.getPost().getId();
        this.userName = comment.getUser().getUsername();
        this.commentContent = comment.getCommentContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.likes = comment.getLikeList().size();
    }
}

package com.sparta.springBlog.controller;

import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.dto.CommentRequestDto;
import com.sparta.springBlog.dto.CommentResponseDto;
import com.sparta.springBlog.security.UserDetailsImpl;
import com.sparta.springBlog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 게시글에 댓글 작성
    @PostMapping("/posts/{postId}/comments")
    public CommentResponseDto createComment(@PathVariable Long postId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto, userDetails.getUser());
    }

    // 게시글의 댓글 수정
    @PutMapping("/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
    }

    // 게시글의 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ApiResponseDto deleteComment(@PathVariable Long commentId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getUser());
    }
}

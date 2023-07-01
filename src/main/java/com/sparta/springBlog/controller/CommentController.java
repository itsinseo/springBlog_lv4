package com.sparta.springBlog.controller;

import com.sparta.springBlog.dto.CommentRequestDto;
import com.sparta.springBlog.dto.CommentResponseDto;
import com.sparta.springBlog.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    // 게시글에 댓글 작성
    @PostMapping("/posts/{id}/comments")
    public CommentResponseDto createComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @PathVariable Long id,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        return null;
    }

    // 게시글의 전체 댓글 조회
    @GetMapping("/posts/{id}/comments")
    public CommentResponseDto getComment(@PathVariable Long id) {
        return null;
    }

    // 게시글의 댓글 수정
    @PutMapping("/posts/{id}/comments/{commentId}")
    public CommentResponseDto updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @PathVariable Long id,
                                            @PathVariable Long commentId,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        return null;
    }

    // 게시글의 댓글 삭제
    @DeleteMapping("/posts/{id}/comments/{commentId}")
    public CommentResponseDto deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @PathVariable Long id,
                                            @PathVariable Long commentId,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        return null;
    }
}

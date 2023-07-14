package com.sparta.springBlog.controller;

import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.dto.CommentRequestDto;
import com.sparta.springBlog.dto.CommentResponseDto;
import com.sparta.springBlog.entity.Comment;
import com.sparta.springBlog.security.UserDetailsImpl;
import com.sparta.springBlog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 게시글에 댓글 작성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long postId,
                                                            @RequestBody CommentRequestDto commentRequestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto commentResponseDto = commentService.createComment(postId, commentRequestDto, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }

    // 게시글의 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponseDto> updateComment(@PathVariable Long commentId,
                                                        @RequestBody CommentRequestDto commentRequestDto) {
        try {
            Comment comment = commentService.findComment(commentId);
            CommentResponseDto commentResponseDto = commentService.updateComment(comment, commentRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponseDto("댓글의 작성자만 삭제/수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 게시글의 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long commentId) {
        try {
            Comment comment = commentService.findComment(commentId);
            ApiResponseDto apiResponseDto = commentService.deleteComment(comment);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponseDto("댓글의 작성자만 삭제/수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }
}

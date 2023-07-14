package com.sparta.springBlog.controller;

import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.security.UserDetailsImpl;
import com.sparta.springBlog.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<ApiResponseDto> likeAndDislike(@PathVariable Long postId,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {

        try {
            ApiResponseDto apiResponseDto = likeService.likeAndDislike(postId, userDetails.getUser());
            return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponseDto("게시글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST.value())
            );
        }
    }
}

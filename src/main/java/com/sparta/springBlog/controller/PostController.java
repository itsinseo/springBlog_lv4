package com.sparta.springBlog.controller;

import com.sparta.springBlog.Jwt.JwtUtil;
import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.dto.PostRequestDto;
import com.sparta.springBlog.dto.PostResponseDto;
import com.sparta.springBlog.security.UserDetailsImpl;
import com.sparta.springBlog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JwtUtil jwtUtil; // 게시글 작성, 수정, 삭제 요청 시 토큰 검사에 사용

    // 게시글 작성
    @PostMapping("/posts")
    public PostResponseDto createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

    // 게시글 모두 불러오기
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // id로 특정 게시글 불러오기
    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // id 로 특정 게시글 수정하기
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @PathVariable Long id,
                                      @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto, userDetails.getUser());
    }

    // id로 특정 게시글 삭제하기
    @DeleteMapping("/posts/{id}")
    public ApiResponseDto deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PathVariable Long id) {
        return postService.deletePost(id, userDetails.getUser());
    }
}

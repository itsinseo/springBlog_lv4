package com.sparta.springBlog.controller;

import com.sparta.springBlog.Jwt.JwtUtil;
import com.sparta.springBlog.dto.PostRequestDto;
import com.sparta.springBlog.dto.PostResponseDto;
import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.service.PostService;
import lombok.RequiredArgsConstructor;
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
    public PostResponseDto createPost(@CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String cookie, @RequestBody PostRequestDto postRequestDto) {
        String userName = checkCookieAndGetUsername(cookie);

        return postService.createPost(postRequestDto, userName);
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
    public PostResponseDto updatePost(@CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String cookie, @PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        String userName = checkCookieAndGetUsername(cookie);

        return postService.updatePost(id, postRequestDto, userName);
    }

    // id로 특정 게시글 삭제하기
    @DeleteMapping("/posts/{id}")
    public ApiResponseDto deletePost(@CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String cookie, @PathVariable Long id) {
        String userName = checkCookieAndGetUsername(cookie);

        return postService.deletePost(id, userName);
    }

    // 쿠키 검사, 완료시 username 을 가져오기 위한 코드
    public String checkCookieAndGetUsername(String cookie) {
        return jwtUtil.getUsernameFromCookie(cookie);
    }
}

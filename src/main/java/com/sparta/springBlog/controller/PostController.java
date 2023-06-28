package com.sparta.springBlog.controller;

import com.sparta.springBlog.dto.PostRequestDto;
import com.sparta.springBlog.dto.PostResponseDto;
import com.sparta.springBlog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
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

    // id, password 로 특정 게시글 수정하기
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto);
    }

    // id로 특정 게시글 삭제하기
    @DeleteMapping("/posts/{id}")
    public PostResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.deletePost(id, postRequestDto);
    }
}

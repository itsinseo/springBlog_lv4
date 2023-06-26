package com.sparta.post.controller;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto);
    }

    @DeleteMapping("/posts/{id}")
    public PostResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.deletePost(id, postRequestDto);
    }
}

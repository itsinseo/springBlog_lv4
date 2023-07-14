package com.sparta.springBlog.controller;

import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.dto.PostRequestDto;
import com.sparta.springBlog.dto.PostResponseDto;
import com.sparta.springBlog.entity.Post;
import com.sparta.springBlog.security.UserDetailsImpl;
import com.sparta.springBlog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto postResponseDto = postService.createPost(postRequestDto, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDto);
    }

    // 게시글 모두 불러오기
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> postResponseDtoList = postService.getPosts();

        return ResponseEntity.status(HttpStatus.OK).body(postResponseDtoList);
    }

    // id로 특정 게시글 불러오기
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.getPost(postId);

        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    // id 로 특정 게시글 수정하기
    @PutMapping("/posts/{postId}")
    public ResponseEntity<ApiResponseDto> updatePost(@PathVariable Long postId,
                                                     @RequestBody PostRequestDto postRequestDto) {
        try {
            Post post = postService.findPost(postId);
            PostResponseDto postResponseDto = postService.updatePost(post, postRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponseDto("게시글의 작성자만 삭제/수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    // id로 특정 게시글 삭제하기
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long postId) {
        try {
            Post post = postService.findPost(postId);
            ApiResponseDto apiResponseDto = postService.deletePost(post);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponseDto("게시글의 작성자만 삭제/수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }
}

package com.sparta.springBlog.controller;

import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.dto.PostRequestDto;
import com.sparta.springBlog.dto.PostResponseDto;
import com.sparta.springBlog.security.UserDetailsImpl;
import com.sparta.springBlog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto postResponseDto = postService.getPost(id);

        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    // id 로 특정 게시글 수정하기
    @PutMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> updatePost(@PathVariable Long id,
                                                     @RequestBody PostRequestDto postRequestDto,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            PostResponseDto postResponseDto = postService.updatePost(id, postRequestDto, userDetails.getUser());
            return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponseDto("작성자만 삭제/수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    // id로 특정 게시글 삭제하기
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long id,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            ApiResponseDto apiResponseDto = postService.deletePost(id, userDetails.getUser());
            return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponseDto("작성자만 삭제/수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }
}

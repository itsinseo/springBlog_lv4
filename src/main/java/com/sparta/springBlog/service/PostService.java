package com.sparta.springBlog.service;

import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.dto.PostRequestDto;
import com.sparta.springBlog.dto.PostResponseDto;
import com.sparta.springBlog.entity.Post;
import com.sparta.springBlog.entity.User;
import com.sparta.springBlog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto, user.getUsername());

        post.setUser(user);

        Post savePost = postRepository.save(post);

        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long postId) {
        Post post = findPost(postId);

        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Post post, PostRequestDto postRequestDto) {
        post.setPostName(postRequestDto.getPostName());
        post.setPostContent(postRequestDto.getPostContent());
        return new PostResponseDto(post);
    }

    public ApiResponseDto deletePost(Post post) {
        postRepository.delete(post);
        return new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value());
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        );
    }
}

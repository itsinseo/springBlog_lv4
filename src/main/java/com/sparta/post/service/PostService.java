package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        // RequestDto -> Entity
        Post post = new Post(postRequestDto);

        // Entity를 DB에 저장
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(savePost);

        return postResponseDto;
    }

    public List<PostResponseDto> getPosts() {
        // DB 조회
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    @Transactional
    public PostResponseDto getPost(Long id) {
        return null;
    }

    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto) {
        return null;
    }

    public Long deletePost(Long id) {
        return null;
    }
}

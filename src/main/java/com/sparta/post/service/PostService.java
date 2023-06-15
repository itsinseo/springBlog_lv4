package com.sparta.post.service;

import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost() {
        return null;
    }

    public List<PostResponseDto> getPosts() {
        return null;
    }
}

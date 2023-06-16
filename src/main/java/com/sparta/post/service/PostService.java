package com.sparta.post.service;

import com.sparta.post.dto.DeleteRequestDto;
import com.sparta.post.dto.DeleteResponseDto;
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
        Post post = new Post(postRequestDto);

        Post savePost = postRepository.save(post);

        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);

        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto) {
        Post post = findPost(id, postRequestDto.getPassword());

        post.update(postRequestDto);

        return new PostResponseDto(post);
    }

    public DeleteResponseDto deletePost(Long id, DeleteRequestDto deleteRequestDto) {
        Post post = findPost(id, deleteRequestDto.getPassword());

        postRepository.delete(post);

        return new DeleteResponseDto();
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        );
    }

    private Post findPost(Long id, String password) {
        return postRepository.findByIdAndPassword(id, password).orElseThrow(() ->
                new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        );
    }
}

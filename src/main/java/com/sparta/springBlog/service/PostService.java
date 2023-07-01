package com.sparta.springBlog.service;

import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.dto.PostRequestDto;
import com.sparta.springBlog.dto.PostResponseDto;
import com.sparta.springBlog.entity.Post;
import com.sparta.springBlog.entity.User;
import com.sparta.springBlog.repository.PostRepository;
import com.sparta.springBlog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto, user.getUsername());
        System.out.println(post);

        post.setUser(user);

        Post savePost = postRepository.save(post);

        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);

        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = findPost(id);
        // 현재 로그인 된 user 와 게시글 작성자가 동일한지 확인
        if (!post.getUser().equals(user)) {
            throw new IllegalArgumentException("user가 일치하지 않습니다.");
        }

        post.setPostName(postRequestDto.getPostName());
        post.setPostContent(postRequestDto.getPostContent());
        return new PostResponseDto(post);
    }

    public ApiResponseDto deletePost(Long id, User user) {
        Post post = findPost(id);
        // 현재 로그인 된 user 와 게시글 작성자가 동일한지 확인
        if (!post.getUser().equals(user)) {
            throw new IllegalArgumentException("user가 일치하지 않습니다.");
        }

        postRepository.delete(post);
        return new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value());
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        );
    }
}

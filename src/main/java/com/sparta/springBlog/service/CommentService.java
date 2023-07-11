package com.sparta.springBlog.service;

import com.sparta.springBlog.dto.CommentRequestDto;
import com.sparta.springBlog.dto.CommentResponseDto;
import com.sparta.springBlog.entity.Comment;
import com.sparta.springBlog.entity.Post;
import com.sparta.springBlog.entity.User;
import com.sparta.springBlog.repository.CommentRepository;
import com.sparta.springBlog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, User user) {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );

        Comment comment = new Comment(post, commentRequestDto.getCommentContent(), user);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}

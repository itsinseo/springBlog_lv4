package com.sparta.springBlog.service;

import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.dto.CommentRequestDto;
import com.sparta.springBlog.dto.CommentResponseDto;
import com.sparta.springBlog.entity.Comment;
import com.sparta.springBlog.entity.Post;
import com.sparta.springBlog.entity.User;
import com.sparta.springBlog.entity.UserRoleEnum;
import com.sparta.springBlog.repository.CommentRepository;
import com.sparta.springBlog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post post = findPost(postId);
        Comment comment = new Comment(post, commentRequestDto.getCommentContent(), user);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, User user) {
        Comment comment = findComment(commentId);

        // 댓글 작성자 일치 또는 관리자인지 여부 확인
        if (!(comment.getUser().equals(user)
                || user.getRole().equals(UserRoleEnum.ADMIN))
        ) {
            throw new IllegalArgumentException("댓글의 작성자가 아닙니다. 수정 권한이 없습니다.");
        }

        comment.setCommentContent(commentRequestDto.getCommentContent());
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    public ApiResponseDto deleteComment(Long commentId, User user) {
        Comment comment = findComment(commentId);

        // 댓글 작성자 일치 또는 관리자인지 여부 확인
        if (!(comment.getUser().equals(user)
                || user.getRole().equals(UserRoleEnum.ADMIN))
        ) {
            throw new IllegalArgumentException("댓글의 작성자가 아닙니다. 수정 권한이 없습니다.");
        }

        commentRepository.delete(comment);

        return new ApiResponseDto("댓글 삭제 성공", HttpStatus.OK.value());
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
    }

    public Comment findComment (Long commentId) {
        // 댓글 존재 여부 확인
        return commentRepository.findById(commentId).orElseThrow(() ->
                new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );
    }
}

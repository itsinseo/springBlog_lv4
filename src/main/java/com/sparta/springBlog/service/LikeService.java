package com.sparta.springBlog.service;

import com.sparta.springBlog.dto.ApiResponseDto;
import com.sparta.springBlog.entity.Comment;
import com.sparta.springBlog.entity.Like;
import com.sparta.springBlog.entity.Post;
import com.sparta.springBlog.entity.User;
import com.sparta.springBlog.repository.CommentRepository;
import com.sparta.springBlog.repository.LikeRepository;
import com.sparta.springBlog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public ApiResponseDto postLikeAndCancel(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        Like like = likeRepository.findByPostAndUser(post, user).orElse(null);

        if (like == null) {
            likeRepository.save(new Like(post, user));
            return new ApiResponseDto("게시글 좋아요 등록 완료", HttpStatus.OK.value());
        } else {
            likeRepository.delete(like);
            return new ApiResponseDto("게시글 좋아요 삭제 완료", HttpStatus.OK.value());
        }
    }

    public ApiResponseDto commentLikeAndCancel(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );

        Like like = likeRepository.findByCommentAndUser(comment, user).orElse(null);

        if (like == null) {
            likeRepository.save(new Like(comment, user));
            return new ApiResponseDto("댓글 좋아요 등록 완료", HttpStatus.OK.value());
        } else {
            likeRepository.delete(like);
            return new ApiResponseDto("댓글 좋아요 삭제 완료", HttpStatus.OK.value());
        }
    }
}

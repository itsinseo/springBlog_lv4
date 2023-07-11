package com.sparta.springBlog.repository;

import com.sparta.springBlog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

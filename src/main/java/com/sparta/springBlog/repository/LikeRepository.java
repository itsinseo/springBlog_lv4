package com.sparta.springBlog.repository;

import com.sparta.springBlog.entity.Like;
import com.sparta.springBlog.entity.Post;
import com.sparta.springBlog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByPostAndUser(Post post, User user);

}

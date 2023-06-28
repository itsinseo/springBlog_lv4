package com.sparta.springBlog.entity;

import com.sparta.springBlog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "postName", nullable = false)
    private String postName;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "postContent", nullable = false)
    private String postContent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post(PostRequestDto postRequestDto, String userName) {
        this.postName = postRequestDto.getPostName();
        this.postContent = postRequestDto.getPostContent();
        this.userName = userName;
    }
}

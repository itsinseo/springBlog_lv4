package com.sparta.springBlog.entity;

import com.sparta.springBlog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post")
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
    @Column(name = "password", nullable = false)
    private String password;

    public Post(PostRequestDto postRequestDto) {
        this.postName = postRequestDto.getPostName();
        this.userName = postRequestDto.getUserName();
        this.postContent = postRequestDto.getPostContent();
        this.password = postRequestDto.getPassword();
    }

    public void update(PostRequestDto postRequestDto) {
        this.postName = postRequestDto.getPostName();
        this.userName = postRequestDto.getUserName();
        this.postContent = postRequestDto.getPostContent();
        this.password = postRequestDto.getPassword();
    }
}

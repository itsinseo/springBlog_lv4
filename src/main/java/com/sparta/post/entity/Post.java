package com.sparta.post.entity;

import com.sparta.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "postName", nullable = false)
    private String postName;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "postContent", nullable = false)
    private String postContent;

    public Post(PostRequestDto postRequestDto) {
        this.postName = postRequestDto.getPostName();
        this.userName = postRequestDto.getUserName();
        this.postContent = postRequestDto.getPostContent();
    }
}

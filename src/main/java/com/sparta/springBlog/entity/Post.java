package com.sparta.springBlog.entity;

import com.sparta.springBlog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "posts")
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postName", nullable = false)
    private String postName;

    @Column(name = "postContent", nullable = false)
    private String postContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto postRequestDto) {
        this.postName = postRequestDto.getPostName();
        this.postContent = postRequestDto.getPostContent();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
